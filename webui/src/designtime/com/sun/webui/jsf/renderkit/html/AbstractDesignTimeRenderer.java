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
package com.sun.webui.jsf.renderkit.html;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.el.EvaluationException;
import javax.faces.el.ValueBinding;
import javax.faces.render.Renderer;

/**
 * Stuff. No fluff.
 *
 * @author gjmurphy
 */
public class AbstractDesignTimeRenderer extends Renderer {
    
    protected static String UNINITITIALIZED_STYLE_CLASS = "rave-uninitialized-text";
    protected static String BORDER_STYLE_CLASS = "rave-design-border";
    
    // Delagatee renderer
    protected Renderer renderer;
    
    public AbstractDesignTimeRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public Object getConvertedValue(FacesContext context, UIComponent component, Object submittedValue)
            throws ConverterException {
        return this.renderer.getConvertedValue(context, component, submittedValue);
    }

    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        this.renderer.encodeEnd(context, component);
    }

    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        this.renderer.encodeChildren(context, component);
    }

    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        this.renderer.encodeBegin(context, component);
    }

    public void decode(FacesContext context, UIComponent component) {
        this.renderer.decode(context, component);
    }

    public String convertClientId(FacesContext context, String clientId) {
        return this.renderer.convertClientId(context, clientId);
    }

    public boolean getRendersChildren() {
        return this.renderer.getRendersChildren();
    }
    
    protected static String addStyleClass(String value, String styleClass) {
        if (value == null) {
            return styleClass;
        } else if (value.indexOf(styleClass) >= 0) {
            return value;
        } else {
            return value + " " + styleClass;
        }
    }
    
    protected static String removeStyleClass(String value, String styleClass) {
        if (value == null || value.indexOf(styleClass) == -1)
            return value;
        int i = value.indexOf(styleClass);
        while (i > 0 && Character.isSpaceChar(value.charAt(i)))
            i--;
        return value.substring(0, i) + value.substring(i + styleClass.length());
    }
    
    protected static Object getDummyData(FacesContext context, ValueBinding vb) {
        Class type = null;
        try {
            type = vb.getType(context);
        } catch (EvaluationException e) {
            type = Object.class;
        }
        return getDummyData(type);
    }

    protected static Object getDummyData(Class clazz) {
        if (clazz.equals(Object.class))
            return null;
        return com.sun.data.provider.impl.AbstractDataProvider.getFakeData(clazz);
    }
    
}
