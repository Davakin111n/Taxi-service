<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>

<html>

<head>

    <title>Jean taxi service</title>

    <link rel="stylesheet" href="<c:url value='/resources/stylesheet/common.css' />"/>
    <link rel="stylesheet" href="<c:url value='/resources/stylesheet/index.css' />"/>

    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <!-- End SlidesJS Required -->

</head>

<body>

<div id="main">

    <%--header for each page--%>
    <%@include file="../jspf/header.jspf" %>

    <div id="slides">
        <%--<img src="resources/images/example-slide-1.jpg" alt="Photo by: Missy S Link: http://www.flickr.com/photos/listenmissy/5087404401/">--%>
        <img src="resources/images/slider/65.png" alt="Slider">
        <%--<img src="resources/images/example-slide-2.jpg" alt="Photo by: Daniel Parks Link: http://www.flickr.com/photos/parksdh/5227623068/">--%>
        <img src="resources/images/slider/43.png" alt="Slider">
        <%--<img src="resources/images/example-slide-3.jpg" alt="Photo by: Mike Ranweiler Link: http://www.flickr.com/photos/27874907@N04/4833059991/">--%>
        <img src="resources/images/slider/3.png" alt="Slider">
        <%--<img src="resources/images/example-slide-4.jpg" alt="Photo by: Stuart SeegerLink: http://www.flickr.com/photos/stuseeger/97577796/">--%>
        <img src="resources/images/slider/55.png" alt="Slider">
        <a href="#" class="slidesjs-previous slidesjs-navigation icon-chevron-left icon-large"></a>
        <a href="#" class="slidesjs-next slidesjs-navigation icon-chevron-right icon-large"></a>
    </div>

    <hr style="width:100%"/>

</div>

<%@include file="../jspf/footer.jspf" %>

</div>

<script>
    $(function () {
        $('#slides').slidesjs({
            width: 840,
            height: 428,
            navigation: false,
            play: {
                active: false,
                // [boolean] Generate the play and stop buttons.
                // You cannot use your own buttons. Sorry.
                effect: "slide",
                // [string] Can be either "slide" or "fade".
                interval: 4000,
                // [number] Time spent on each slide in milliseconds.
                auto: true,
                // [boolean] Start playing the slideshow on load.
                swap: true,
                // [boolean] show/hide stop and play buttons
                pauseOnHover: false,
                // [boolean] pause a playing slideshow on hover
                restartDelay: 2500
                // [number] restart delay on inactive slideshow
            }

        });
    });
</script>
</body>
</html>