<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="tab-details">
	<ycommerce:testId code="productDetails_content_label">
		<p>
			${ycommerce:sanitizeHTML(product.description)}
		<p>
	</ycommerce:testId>

	<c:if test="${product.daysBeforeOffline > 0}">
	    <br><spring:theme code="product.product.details.future.days" arguments="${product.daysBeforeOffline}"/>
	</c:if>

</div>