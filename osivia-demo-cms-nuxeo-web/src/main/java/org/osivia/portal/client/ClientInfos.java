/*
 * (C) Copyright 2014 OSIVIA (http://www.osivia.com) 
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 */
package org.osivia.portal.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * @author David Chevrier
 */
public class ClientInfos {
    
    private static final String HEADER_ACCEPT = "accept";
    public static final String LE_MIME_TYPE = "application/x-nuxeo-liveedit";

    private boolean clientHasLiveEditInstalled = false;
    private List<String> nxLiveEditMimeTypes = new ArrayList<String>(1);
    
    public ClientInfos(HttpServletRequest request){
        setLiveEditMimeTypes(request);
    }

    public List<String> getNxLiveEditMimeTypes() {
        return this.nxLiveEditMimeTypes;
    }

    public boolean isClientHasLiveEditInstalled() {
        return this.clientHasLiveEditInstalled;
    }

    public void setLiveEditMimeTypes(HttpServletRequest request) {
        String accept = request.getHeader(HEADER_ACCEPT);
        if (accept == null) {
            return;
        }

        String[] accepted = accept.split(",");
        for (String acceptHeader : accepted) {
            if (acceptHeader != null) {
                acceptHeader = acceptHeader.trim();
            } else {
                continue;
            }
            if (acceptHeader.startsWith(LE_MIME_TYPE)) {
                clientHasLiveEditInstalled = true;
                String[] subTypes = acceptHeader.split(";");

                for (String subType : subTypes) {
                    // accept both forms:
                    // application/x-nuxeo-liveedit:mimetype1;mimetype2
                    // application/x-nuxeo-liveedit;ext0="mimetype1";ext1="mimetype2"
                    int equalQuoteIndex = subType.indexOf("=\"");
                    String valueSubType = subType;
                    if (equalQuoteIndex >= 0 && subType.length() > equalQuoteIndex + 3) {
                        valueSubType = subType.substring(equalQuoteIndex + 2, subType.length() - 1);
                    }
                    nxLiveEditMimeTypes.add(valueSubType.replace("!", "/"));
                }
            }
        }
    }
    
}
