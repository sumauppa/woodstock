<jsp:root version="2.0" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
  <jsp:directive.page contentType="text/html" />
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
    <webuijsf:page >
      <webuijsf:html>
        <f:loadBundle basename="com.sun.webui.jsf.example.resources.Resources" var="msgs" />
        <webuijsf:head title="#{msgs.cbrb_clientsideCbTitle}">
	  <webuijsf:link rel="shortcut icon" url="/images/favicon.ico" type="image/x-icon" />
        </webuijsf:head>
        <webuijsf:body>
            <webuijsf:form id="form1">

              <!-- Masthead -->
              <webuijsf:masthead id="Masthead" productImageURL="/images/example_primary_masthead.png"
                productImageDescription="#{msgs.mastheadAltText}" 
                userInfo="test_user"
                serverInfo="test_server" />     
                         
              <!-- Bread Crumb Component -->
              <webuijsf:breadcrumbs id="breadcrumbs">
                <webuijsf:hyperlink actionExpression="#{CheckboxRadiobuttonBean.showExampleIndex}" text="#{msgs.exampleTitle}"
                  onMouseOver="javascript:window.status='#{msgs.index_breadcrumbMouseOver}'; return true;"
                  onMouseOut="javascript: window.status=''; return true" />
                <webuijsf:hyperlink actionExpression="#{CheckboxRadiobuttonBean.showCbRbIndex}" text="#{msgs.cbrb_indexTitle}"
                  onMouseOver="javascript:window.status='#{msgs.index_breadcrumbMouseOver}'; return true;"
                  onMouseOut="javascript: window.status=''; return true" />
                <webuijsf:hyperlink text="#{msgs.cbrb_clientsideCbTitle}"/>
              </webuijsf:breadcrumbs>
                       
              <!-- Page Title -->
              <webuijsf:contentPageTitle title="#{msgs.cbrb_clientsideCbTitle}" />
              <webuijsf:script>
                  
                    // Toggle enable/disable state of checkbox client-side.
                    function toggleCheckboxState() {
                        var cb1 = document.getElementById("form1:cbServer");
                        var cb2 = document.getElementById("form1:cbVolume");
                        var cb3 = document.getElementById("form1:cbPool");
                    
                        cb1.setProps({disabled: !cb1.getProps().disabled});
                        cb2.setProps({disabled: !cb2.getProps().disabled});
                        cb3.setProps({disabled: !cb3.getProps().disabled});
                        
                        return false;
                    }
                  
              </webuijsf:script>          
              
              <webuijsf:markup tag="div" styleClass="#{themeStyles.CONTENT_MARGIN}">
                       
                <!-- Use HTML table for layout.  Note that if we had included this
                     content within the body of contentPageTitle, then we would need
                     to wrap the HTML markup in the f:verbatim tag.  webuijsf:markup could
                     also be used but that is more heavyweight (slower). -->
                <table>
                  <tr>
                    <td>
                      <!-- Checkbox -->
                      <webuijsf:label id="CheckboxLabel" text="#{msgs.cbrb_checkboxLabel}"/>
                    </td>
                    <td>
                      <div>
                        <webuijsf:checkbox id="cbServer" name="cbgrp" label="#{msgs.cbrb_radioButton1}"/>                        
                      </div>
                      <div>
                        <webuijsf:checkbox id="cbVolume" name="cbgrp" label="#{msgs.cbrb_radioButton2}"/>
                      </div>
                      <div>
                        <webuijsf:checkbox id="cbPool" name="cbgrp" label="#{msgs.cbrb_radioButton3}"/>
                      </div>                                          
                    </td>
                  </tr>               
                </table>
                <br/>

                <!-- Toggle State Button -->
                <webuijsf:button id="ToggleStateButton" text="#{msgs.cbrb_testCase_toggleCheckboxState}" onClick="return toggleCheckboxState();" />
                                           
              </webuijsf:markup>
            </webuijsf:form>
          </webuijsf:body> 
      </webuijsf:html>  
    </webuijsf:page>
  </f:view>
</jsp:root>                       
