$(document).ready(function() {
		var windowWidth;
				$('#side-menu').metisMenu();

				// 菜单切换
				$('.navbar-minimalize').click(function() {
					$("body").toggleClass("mini-navbar");
					SmoothlyMenu();
					
					setMainRight();
				});

				$('#side-menu>li').click(function() {
					if($('body').hasClass('mini-navbar')) {
						NavToggle();
					}
				});
				$('#side-menu>li li a').click(function() {
					if($(window).width() < 769) {
						NavToggle();
					}
				});

				function NavToggle() {
					$('.navbar-minimalize').trigger('click');
				}

				function SmoothlyMenu() {
					if(!$('body').hasClass('mini-navbar')) {
						$('#side-menu').hide();
						setTimeout(
							function() {
								$('#side-menu').fadeIn(500);
							}, 100);
					} else if($('body').hasClass('fixed-sidebar')) {
						$('#side-menu').hide();
						setTimeout(
							function() {
								$('#side-menu').fadeIn(500);
							}, 300);
					} else {
						$('#side-menu').removeAttr('style');
					}
				}

				$(window).bind("load resize", function() {
					if($(this).width() < 769) {
						$('body').addClass('mini-navbar');
						$('.navbar-static-side').fadeIn();
					}
					windowWidth = $(this).width();
					setMainRight();
				});
				function setMainRight(){
					var width = windowWidth - $(".main-left").width() -30;
					$(".main-right").width(width);
				}
			});