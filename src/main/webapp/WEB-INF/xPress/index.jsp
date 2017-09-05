<html>
	<head>
		<meta charset="UTF-8">
		<title>RICOne - xPress API</title>
		<!--<link rel="icon" type="image/png" href="resources/images/favicon-32x32.png" sizes="32x32" />
		<link rel="icon" type="image/png" href="resources/images/favicon-16x16.png" sizes="16x16" />-->
		<link rel="icon" href="resources/images/favicon.ico">
		<link href="resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="resources/css/style.css" rel="stylesheet" media="screen">
		<link href="resources/css/font-awesome.min.css" rel="stylesheet">
		<link href='resources/css/typography.css' media='screen' rel='stylesheet' type='text/css'/>
		<link href='resources/css/reset.css' media='screen' rel='stylesheet' type='text/css'/>
		<link href='resources/css/screen.css' media='screen' rel='stylesheet' type='text/css'/>
		<link href='resources/css/reset.css' media='print' rel='stylesheet' type='text/css'/>
		<link href='resources/css/print.css' media='print' rel='stylesheet' type='text/css'/>
		<script src='resources/lib/object-assign-pollyfill.js' type='text/javascript'></script>
		<script src='resources/lib/jquery-1.8.0.min.js' type='text/javascript'></script>
		<script src='resources/lib/jquery.slideto.min.js' type='text/javascript'></script>
		<script src='resources/lib/jquery.wiggle.min.js' type='text/javascript'></script>
		<script src='resources/lib/jquery.ba-bbq.min.js' type='text/javascript'></script>
		<script src='resources/lib/handlebars-2.0.0.js' type='text/javascript'></script>
		<script src='resources/lib/js-yaml.min.js' type='text/javascript'></script>
		<script src='resources/lib/lodash.min.js' type='text/javascript'></script>
		<script src='resources/lib/backbone-min.js' type='text/javascript'></script>
		<script src='resources/swagger-ui.js' type='text/javascript'></script>
		<script src='resources/lib/highlight.9.1.0.pack.js' type='text/javascript'></script>
		<script src='resources/lib/highlight.9.1.0.pack_extended.js' type='text/javascript'></script>
		<script src='resources/lib/jsoneditor.min.js' type='text/javascript'></script>
		<script src='resources/lib/marked.js' type='text/javascript'></script>
		<script src='resources/lib/swagger-oauth.js' type='text/javascript'></script>
		<!-- Some basic translations -->
		<script type="text/javascript">
			$(function(){function e(){"console"in window&&console.log.apply(console,arguments)}var o=window.location.search.match(/url=([^&]+)/);o=o&&o.length>1?decodeURIComponent(o[1]):"http://petstore.swagger.io/v2/swagger.json",hljs.configure({highlightSizeThreshold:5e3}),window.SwaggerTranslator&&window.SwaggerTranslator.translate(),window.swaggerUi=new SwaggerUi({url:"/api/swagger/api-docs",dom_id:"swagger-ui-container",validatorUrl:null,supportedSubmitMethods:["get","post","put","delete","patch"],onComplete:function(e,o){"function"==typeof initOAuth&&initOAuth({clientId:"your-client-id",clientSecret:"your-client-secret-if-required",realm:"your-realms",appName:"your-app-name",scopeSeparator:",",additionalQueryStringParams:{}}),window.SwaggerTranslator&&window.SwaggerTranslator.translate()},onFailure:function(o){e("Unable to Load SwaggerUI")},docExpansion:"none",jsonEditor:!1,defaultModelRendering:"schema",showRequestHeaders:!1}),window.swaggerUi.load()});
		</script>
	</head>
	<body class="swagger-section inner-page compressed-main">
		<header>
			<div id='header'>
				<div class="container">
					<nav class="navbar-inverse header_nav">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header"> 
							<a class="navbar-brand header_logo" href="/api">
								<img src="resources/images/logo.png" alt="New York State Regional Information Centers">
							</a> 
						</div>
						<div class="navbar-right">
							<div class="pull-right hidden-xs">
								<form id='api_selector'>
									<div class='input'>
										<a id='auth_container' class="authorize__btn"></a>
									</div>
									<div class='input'>
										<a id="explore" class="header__btn" href="#" data-sw-translate>Explore</a>
									</div>
								</form>
							</div>
						</div>
					</nav>					
				</div>
			</div>
		</header>
		<main>
			<div class="bg-white sec-spaced pad_bot_none">			
				<div class="container">
					<div class="row">
						<div class="col-xs-12 permalink_dm">
							<a href="http://www.ricone.org">Home</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;<a href="http://www.ricone.org/vendors/">Vendors</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;<a href="http://www.ricone.org/vendors/ric-one-api/">RIC One API</a>
						</div>
					</div>
				</div>
			</div>
			<div class="bg-white sec-spaced pad_bot_none">
				<div class="container">
					<div id="message-bar" class="swagger-ui-wrap" data-sw-translate>&nbsp;</div>
					<div id="swagger-ui-container" class="swagger-ui-wrap"></div>
				</div>
			</div>
		</main>
		<footer>
			<div class="scrollup">
				<i class="fa fa-chevron-up"></i>
			</div>
		</footer>
		
		<script type="text/javascript">
			$(document).ready(function() {
				$('#auth_container').click(function(e) {  
				  $('#theinput').val('Bearer ');
				});
			});
		</script>
		
		
		<script type="text/javascript">
			function comboInit(e)
			{
				var theinput = document.getElementById('theinput');
				var n = e.selectedIndex;
				var t = e.options[n].value;
				if(t != 'Bearer')
				{
					"" == theinput.value && (theinput.value = t) 
				}
				else
				{
				"" == theinput.value && (theinput.value = "Bearer ") 
				}
			}
			function combo(e,n)
			{
				n=document.getElementById(n);
				var t = e.selectedIndex; 
				var	o = e.options[t].value;
				
				if(e.options[t].innerHTML != 'Bearer')
				{
					n.value = o;
				}
				else
				{
					n.value = "Bearer ";
				}
			}
		</script> 
		<script>
			if (navigator.userAgent.match(/IEMobile\/10\.0/)) {
				var msViewportStyle = document.createElement('style')
				msViewportStyle.appendChild(
					document.createTextNode(
						'@-ms-viewport{width:auto!important}'
					)
				)
				document.querySelector('head').appendChild(msViewportStyle)
			}
			
			$(function() {
				if($("html").hasClass("touch")) 
				{
					$( ".has-dropdown i" ).click(function() {
						$(this).siblings(".dropdown-mob").toggleClass("drop-visible");
					});
						
				}
			
				$(window).scroll(function () {
					if ($(this).scrollTop() > 300) {
						$('.scrollup').fadeIn();
					} else {
						$('.scrollup').fadeOut();
					}
				});
		
				$('.scrollup').click(function () {
					$('html, body').animate({
						scrollTop: 0
					}, 300);
					return false;
				});
			
				$( ".toc a" ).on('click',function() {
					var to = $(this).attr('href');
					$('html, body').animate({
							scrollTop: $(to).offset().top - ($("body > header").height() + 15)
						}, 500);
					return false;
				});
			
				$( ".doc-page a[href^=\"#\"]" ).on('click',function() {
					var to = $(this).attr('href').substring(1);
					$('html, body').animate({
							scrollTop: $("[id=\""+to+"\"]").offset().top - ($("body > header").height() + 15)
						}, 500);
					return false;
				});
			
				$( ".m-item-hold i[data-href]" ).click(function(ev) {
					ev.preventDefault();
					window.open($(this).attr("data-href"), '_blank'); 
				});
					
			});
		</script>
	</body>
</html>
