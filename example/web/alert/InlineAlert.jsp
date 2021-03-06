<jsp:root version="2.0" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
<jsp:directive.page contentType="text/html"/>
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
      <webuijsf:head id="head" title="#{msgs.alert_inlineTitle}">
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
            <webuijsf:hyperlink id="indexPageLink"
                text="#{msgs.index_title}"
                toolTip="#{msgs.index_title}"
                actionExpression="#{InlineAlertBean.showExampleIndex}"
                onMouseOver="javascript:window.status='#{msgs.index_breadcrumbMouseOver}'; return true;"
                onMouseOut="javascript: window.status=''; return true" />
            <webuijsf:hyperlink id="alertIndexLink"
                text="#{msgs.alert_title}"
                toolTip="#{msgs.alert_title}"
                actionExpression="#{InlineAlertBean.showAlertIndex}"
                onMouseOver="javascript:window.status='#{msgs.alert_breadcrumbMouseOver}'; return true;"
                onMouseOut="javascript: window.status=''; return true" />
            <webuijsf:hyperlink id="inlineAlertLink" text="#{msgs.alert_inlineTitle}" />
          </webuijsf:breadcrumbs>
                   
          <!-- Alert -->
          <webuijsf:alert id="alert"                
              summary="#{InlineAlertBean.alertSummary}"
              detail="#{InlineAlertBean.alertDetail}"
              type="#{InlineAlertBean.alertType}"               
              rendered="#{InlineAlertBean.alertRendered}" >
             <f:facet name="alertLink">
                  <webuijsf:imageHyperlink id="imageLink"
                      rendered="#{InlineAlertBean.linkRendered}"
                      icon="HREF_LINK"
                      target="help_window"
                      url="HelpAlert.jsp"
                      text="#{msgs.alert_helpLinkText}"
                      toolTip="#{msgs.alert_helpLinkTooltip}"
                      onMouseOver="javascript:window.status='#{msgs.alert_helpLinkMouseOver}'; return true;"
                      onMouseOut="javascript: window.status=''; return true"
                      onClick="javascript: var win = window.open('','help_window','height=500,width=750,top='+((screen.height-(screen.height/1.618))-(500/2))+',left='+((screen.width-650)/2)+',resizable'); win.focus();"/> 
             </f:facet>
          </webuijsf:alert>
          
          <!-- Content Page Title -->
          <webuijsf:contentPageTitle id="contentPageTitle"              
              title="#{msgs.alert_inlineTitle}"
              helpText="#{msgs.alert_line1Text}" > 
              
            <f:facet name="pageButtonsBottom">
	      <webuijsf:panelGroup id="pageButtonsGroupBottom"> 
                <!-- OK Button -->                
                <webuijsf:button id="okButton"                 
                  text="#{msgs.alert_okButton}" 
                  actionExpression="#{InlineAlertBean.handleAction}"
		  primary="true"
		  disabled="#{InlineAlertBean.disabled}"
                  actionListenerExpression="#{InlineAlertBean.processButtonAction}"/>

		<!-- Restart Button -->                
                <webuijsf:button id="restartButton"                 
                  text="#{msgs.alert_restartButton}" 
                  actionExpression="#{InlineAlertBean.restart}"
                  immediate="true"/>                  
              </webuijsf:panelGroup>
            </f:facet>
            
            <webuijsf:markup tag="div" styleClass="#{themeStyles.CONTENT_MARGIN}">                                    
                <webuijsf:markup tag="br" singleton="true" /> 
                
                <!-- Number Entry Field -->
                <webuijsf:textField id="numberTextField"
                    label="#{msgs.alert_guess}"
                    columns="4"
                    toolTip="#{msgs.alert_textFieldTitle}"
                    text="#{InlineAlertBean.fieldValue}"
                    required="true"
                    validatorExpression="#{InlineAlertBean.validateFieldEntry}" /> 
            </webuijsf:markup>          
            
          </webuijsf:contentPageTitle>                     
        </webuijsf:form>
      </webuijsf:body>
    </webuijsf:html>  
  </webuijsf:page>
</f:view>

</jsp:root>
