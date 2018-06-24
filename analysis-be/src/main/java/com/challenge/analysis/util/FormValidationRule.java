package com.challenge.analysis.util;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum FormValidationRule {

    FORM_EXIST_VALIDATION_RULE {
        @Override
        public Boolean validate(Map<String, List<Element>> context, Document document) {
            List<Element> formElements = document.select(HTMLAnalysisConstants.FORM_ELEMENT);
            context.put(HTMLAnalysisConstants.VALID_FORMS, formElements);
            return formElements.size() > 0;
        }

    },
    POST_FORM_EXIST_VALIDATION_RULE {
        @Override
        public Boolean validate(Map<String, List<Element>> context, Document document) {
            List<Element> forms = context.get(HTMLAnalysisConstants.VALID_FORMS);
            List<Element> postForms = forms.stream()
                    .filter(this::formsWithPostMethods)
                    .collect(Collectors.toList());
            context.put(HTMLAnalysisConstants.VALID_FORMS, postForms);
            return postForms.size() > 0;
        }

        Boolean formsWithPostMethods(Element form) {
            return HTMLAnalysisConstants.POST_METHOD.equalsIgnoreCase(form.attr(HTMLAnalysisConstants.METHOD_ATTRIBUTE));
        }
    },

    FORM_ACTION_VALIDATION_RULE {
        @Override
        public Boolean validate(Map<String, List<Element>> context, Document document) {
            List<Element> forms = context.get(HTMLAnalysisConstants.VALID_FORMS);
            List<Element> loginForms = forms.stream()
                    .filter(this::formsWithLoginActions)
                    .collect(Collectors.toList());
            context.put(HTMLAnalysisConstants.VALID_FORMS, loginForms);
            return loginForms.size() > 0;
        }

        Boolean formsWithLoginActions(Element form) {
            return HTMLAnalysisConstants.FORM_ACTION_PATTERN.matcher(form.attr(HTMLAnalysisConstants.ACTION_ATTRIBUTE)).matches();
        }
    },

    FORM_USERNAME_VALIDATION_RULE {
        @Override
        public Boolean validate(Map<String, List<Element>> context, Document document) {
            List<Element> forms = context.get(HTMLAnalysisConstants.VALID_FORMS);
            List<Element> validUserNameForms = forms.stream()
                    .filter(this::formsWithValidUserNamesTextFields)
                    .collect(Collectors.toList());
            context.put(HTMLAnalysisConstants.VALID_FORMS, validUserNameForms);
            return validUserNameForms.size() > 0;
        }

        Boolean formsWithValidUserNamesTextFields(Element form) {
            return form.select(HTMLAnalysisConstants.INPUT_ELEMENT).stream()
                    .filter(this::inputWithTextFields)
                    .anyMatch(this::textFieldsWithValidUserNames);
        }

        Boolean inputWithTextFields(Element element) {
            return HTMLAnalysisConstants.TEXT.equals(element.attr(HTMLAnalysisConstants.TYPE_ATTRIBUTE)) ||
                    HTMLAnalysisConstants.EMAIL.equals(element.attr(HTMLAnalysisConstants.TYPE_ATTRIBUTE));
        }

        Boolean textFieldsWithValidUserNames(Element element) {
            return ConfigUtil.USER_NAMES_LIST.contains(element.attr(HTMLAnalysisConstants.NAME_ATTRIBUTE));
        }
    },

    FORM_PASSWORD_VALIDATION_RULE {
        @Override
        public Boolean validate(Map<String, List<Element>> context, Document document) {
            List<Element> forms = context.get(HTMLAnalysisConstants.VALID_FORMS);
            List<Element> validPasswordForms = forms.stream()
                    .filter(this::formsWithValidPasswordFields)
                    .collect(Collectors.toList());
            context.put(HTMLAnalysisConstants.VALID_FORMS, validPasswordForms);
            return validPasswordForms.size() > 0;
        }

        Boolean formsWithValidPasswordFields(Element form) {
            return form.select(HTMLAnalysisConstants.INPUT_ELEMENT).stream()
                    .filter(this::inputWithPasswordFields)
                    .anyMatch(this::textFieldsWithValidPasswordNames);
        }

        Boolean inputWithPasswordFields(Element element) {
            return HTMLAnalysisConstants.PASSWORD.equals(element.attr(HTMLAnalysisConstants.TYPE_ATTRIBUTE));
        }

        Boolean textFieldsWithValidPasswordNames(Element element) {
            return ConfigUtil.PASSWORD_NAMES_LIST.contains(element.attr(HTMLAnalysisConstants.NAME_ATTRIBUTE));
        }
    };

    public abstract Boolean validate(Map<String, List<Element>> context, Document document);
}
