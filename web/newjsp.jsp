<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html>
 
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>User Input Form (JSF in JSP syntax)</title>
  </head>
  <body>
    <f:view>
      <h1><h:outputText value="User Input Form"/></h1>
      <h:form id="UserEntryForm">
        <h:outputText value="Enter Your Name:"/>
        <h:inputText value="#{userBean.name}" />
        <h:commandButton action="send" value="OK" />
      </h:form>
    </f:view>
  </body>
</html>