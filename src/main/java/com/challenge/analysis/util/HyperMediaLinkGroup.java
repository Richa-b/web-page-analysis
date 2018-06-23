package com.challenge.analysis.util;

import com.challenge.analysis.model.HyperMediaLinkDetail;

public enum HyperMediaLinkGroup {
    INTERNAL,
    EXTERNAL;

    public static HyperMediaLinkGroup getLinkGroup(HyperMediaLinkDetail hyperMediaLinkDetail,
                                                       String currentDomain) {
        return HTMLAnalysisUtil.isInternalDomain(currentDomain, hyperMediaLinkDetail.getUrl()) ?
                HyperMediaLinkGroup.INTERNAL :
                HyperMediaLinkGroup.EXTERNAL;
    }
}
