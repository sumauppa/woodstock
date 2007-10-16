<jsp:root version="2.0" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>    
        <!--
      The contents of this file are subject to the terms
      of the Common Development and Distribution License
      (the License).  You may not use this file except in
      compliance with the License.
      
      You can obtain a copy of the license at
      https://woodstock.dev.java.net/public/CDDLv1.0.html.
      See the License for the specific language governing
      permissions and limitations under the License.
      
      When distributing Covered Code, include this CDDL
      Header Notice in each file and include the License file
      at https://woodstock.dev.java.net/public/CDDLv1.0.html.
      If applicable, add the following below the CDDL Header,
      with the fields enclosed by brackets [] replaced by
      you own identifying information:
      "Portions Copyrighted [year] [name of copyright owner]"
      
      Copyright 2007 Sun Microsystems, Inc. All rights reserved.
    -->
        <webuijsf:page>
            <f:loadBundle basename="com.sun.webui.jsf.example.resources.Resources" var="msgs" />
            <webuijsf:html id="html">  
                <webuijsf:head id="head" title="#{msgs.field_basicTitle}">                
                    <webuijsf:link rel="shortcut icon" url="/images/favicon.ico" type="image/x-icon" />
                </webuijsf:head>
                <webuijsf:body id="body">
                    <webuijsf:form id="form">                                                              
                        
                        <!-- Masthead -->
                        <webuijsf:masthead id="masthead"
                                           productImageURL="/images/example_primary_masthead.png"
                                           productImageHeight="40"
                                           productImageWidth="188"
                                           userInfo="test_user" 
                                           serverInfo="test_server"
                                           productImageDescription="#{msgs.mastheadAltText}" />
                        
                        <!-- Breadcrumbs -->   
                        <webuijsf:breadcrumbs id="breadcrumbs">
                            <webuijsf:hyperlink id="indexLink"
                                                text="#{msgs.index_title}"
                                                toolTip="#{msgs.index_title}"
                                                actionExpression="#{TextInputBean.showExampleIndex}" 
                                                onMouseOver="javascript:window.status='#{msgs.index_breadcrumbMouseOver}'; return true;"
                                                onMouseOut="javascript: window.status=''; return true" />
                            <webuijsf:hyperlink id="tabsetIndexLink"
                              text="#{msgs.field_indexTitle}"
                              toolTip="#{msgs.field_indexTitle}"
                              actionExpression="#{TextInputBean.showTextInputIndex}"
                              onMouseOver="javascript:window.status='#{msgs.field_breadcrumbMouseOver}'; return true;"
                              onMouseOut="javascript: window.status=''; return true" />
                            <webuijsf:hyperlink id="exampleLink" text="#{msgs.field_basicTitle}"/>
                        </webuijsf:breadcrumbs>
                        
                        <!-- Content Page Title -->
                        <webuijsf:contentPageTitle id="contentPageTitle" title="#{msgs.field_basicTitle}" /> 
                        <webuijsf:markup tag="div" styleClass="#{themeStyles.CONTENT_MARGIN}">
                            <br />
                            <table border="0">                
                                <tr>
                                    <td valign="top">              
                                        <webuijsf:label id="textFieldLabel" 
                                                        for="textField"
                                                        style="padding-right:10px;"
                                                        text="#{msgs.field_textFieldLabel}" />                      
                                    </td>
                                    <td>         
                                        <!-- Text Field --> 
                                        <webuijsf:textField id="textField"                    
                                                            text="#{TextInputBean.textFieldValue}"                      
                                                            disabled="#{TextInputBean.textFieldDisabled}"                    
                                                            toolTip="#{msgs.field_textFieldTitle}" />                                     
                                    </td>
                                </tr>
                                <tr><td colspan="3"><div style="height:15px"/></td></tr>
                                <tr>
                                    <td valign="top">                  
                                        <webuijsf:label id="passwordLabel"
                                                        for="password"
                                                        style="padding-right:10px;"
                                                        text="#{msgs.field_passwordLabel}" />                     
                                    </td>
                                    <td>                  
                                        <!-- Password Field -->              
                                        <webuijsf:passwordField id="password"                                       
                                                                password="#{TextInputBean.passwordValue}" 
                                                                disabled="#{TextInputBean.passwordDisabled}"                    
                                                                toolTip="#{msgs.field_passwordTitle}" />                
                                    </td>
                                </tr>
                                <tr><td colspan="3"><div style="height:15px"/></td></tr>
                                <tr>
                                    <td valign="top">                                    
                                        <webuijsf:label id="textAreaLabel"
                                                        for="textArea" 
                                                        text="#{msgs.field_textAreaLabel}" />                      
                                    </td><td>                  
                                        <!-- TextArea Field -->              
                                        <webuijsf:textArea id="textArea"                      
                                                           text="#{TextInputBean.textAreaValue}"
                                                           rows="4"
                                                           columns="20"
                                                           disabled="#{TextInputBean.textAreaDisabled}"                     
                                                           toolTip="#{msgs.field_textAreaTitle}" />
                                        
                                    </td>
                                </tr>
                                <tr><td colspan="3"><div style="height:15px"/></td></tr>
                            </table>        
                            
                            <webuijsf:panelGroup id="pageActionsGroup" block="true" style="padding:25px 0px 0px 0px;">
                                
                                <!-- Submit Button -->            
                                <webuijsf:button id="submitButton"                       
                                                 text="#{msgs.field_submitButton}"                     
                                                 actionExpression="showTextInputResults" />                            
                                
                                <!-- Preset Button -->
                                <webuijsf:button id="presetButton"              
                                                 immediate="true"
                                                 text="#{msgs.field_presetFieldsButton}"
                                                 actionListenerExpression="#{TextInputBean.presetFields}"/> 
                                
                                <!-- Test Case Menu -->                     
                                <webuijsf:dropDown id="testCaseMenu"  
                                                   immediate="true"
                                                   submitForm="true"
                                                   forgetValue="true"
                                                   toolTip="#{msgs.field_dropDownToolTip}"
                                                   items="#{TextInputBean.testCaseOptions}"
                                                   actionListenerExpression="#{TextInputBean.processMenuSelection}" />             
                            </webuijsf:panelGroup>     
                        </webuijsf:markup>            
                        
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>  
        </webuijsf:page>
    </f:view>
    
</jsp:root>
