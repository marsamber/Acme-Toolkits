<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="any.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="any.toolkit.form.label.code" path="code"/>		
	<acme:input-money code="any.toolkit.form.label.totalPrice" path="totalPrice"/>
	<acme:input-money readonly="true" code="any.toolkit.form.label.totalPriceEUR" path="totalPriceEUR"/>
	<acme:input-money readonly="true" code="any.toolkit.form.label.totalPriceUSD" path="totalPriceUSD"/>
	<acme:input-money readonly="true" code="any.toolkit.form.label.totalPriceGBP" path="totalPriceGBP"/>		
	<acme:input-textarea code="any.toolkit.form.label.description" path="description"/>
	<acme:input-textarea code="any.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
	<acme:input-url code="any.toolkit.form.label.link" path="link"/>	
	
	<acme:button code="any.toolkit.form.button.items" action="/any/toolkit-item/list?toolkitId=${id}"/>
</acme:form>
