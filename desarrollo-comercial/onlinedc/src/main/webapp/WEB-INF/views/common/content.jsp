<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Wrapper-->
<div id="wrapper">

	<!-- Navigation -->
	<div ng-include="'common/navigation'"></div>

	<!-- Page wraper -->
	<!-- 
    ng-class with current state name give you the ability to extended customization your view 
    {{$state.current.name}} 
    -->

	<div id="page-wrapper" class="gray-bg" style="min-width: 320px;">

		<!-- Page wrapper -->
		<div ng-include="'common/topnavbar'"></div>

		<!-- Main view  -->
		<div ui-view></div>

		<!-- Footer -->
		<div ng-include="'common/footer'"></div>

	</div>
</div>
<!-- End wrapper-->