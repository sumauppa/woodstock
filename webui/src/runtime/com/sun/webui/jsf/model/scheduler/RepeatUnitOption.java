/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License).  You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at
 * https://woodstock.dev.java.net/public/CDDLv1.0.html.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at https://woodstock.dev.java.net/public/CDDLv1.0.html.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * you own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 */
/*
 * NoRepeatOption.java
 *
 * Created on July 14, 2005, 2:19 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.sun.webui.jsf.model.scheduler;

import javax.faces.context.FacesContext; 
import com.sun.webui.jsf.model.Option; 
/**
 *
 * @author avk
 */
public class RepeatUnitOption extends Option {
    
    /** Creates a new instance of RepeatMonthly */
    public RepeatUnitOption(RepeatUnit repeatUnit) {
        this.setValue(repeatUnit);
    }
    
    public String getLabel() {
        return ((RepeatUnit)getValue()).getLabel(FacesContext.getCurrentInstance());
    }
}
  
