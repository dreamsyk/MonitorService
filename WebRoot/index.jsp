<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;

	String memory_data_url = basePath + "/memory/data";
	String cpu_data_url = basePath + "/cpu/data";
	
%>

<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>System Monitor</title>
		
		<!-- 新 Bootstrap 核心 CSS 文件 -->
		<link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
		
		<!-- 可选的Bootstrap主题文件（一般不使用） -->
		<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-theme.min.css"></script>
		
		<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
		<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
		
		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
		
	    <script type="text/javascript" src="<%=basePath%>/highstock/highstock.js" ></script>
	    <script type="text/javascript" src="<%=basePath%>/highstock/exporting.js" ></script>
				
		<script type="text/javascript">
		
			$(function() {
			
			Highcharts.setOptions({
					global : {
						useUTC : false //时区设置
					},
					credits : {
						enabled : false //去掉右下角的标志
					}
				});
				
				$.getJSON('<%=memory_data_url%>', function (data) {
			    
			        // Create the chart
			        $('#memory_chart').highcharts('StockChart', {
			            
					chart:{
				        events:{
				            load:function(){
				                var series = this.series[0]
				                setInterval(function(){
				                $.getJSON('<%=memory_data_url%>',function(res){
				                    $.each(res,function(i,v){
				                        series.addPoint(v)
				                    })
				                })
				                },3000)
				            }
				        }
				        },
			            
			            rangeSelector : {
			                selected : 1
			            },
			
			            title : {
			                text : 'Memory'
			            },
			
			            series : [{
			                name : 'Utilized (%)',
			                data : data,
			                tooltip: {
			                    valueDecimals: 2
			                }
			            }]
			        });
			    });
			    
			});
			
			
			$.getJSON('<%=cpu_data_url%>', function (data) {
			    
			        // Create the chart
			        $('#cpu_chart').highcharts('StockChart', {
			            
					chart:{
				        events:{
				            load:function(){
				                var series = this.series[0]
				                setInterval(function(){
				                $.getJSON('<%=cpu_data_url%>',function(res){
				                    $.each(res,function(i,v){
				                        series.addPoint(v)
				                    })
				                })
				                },3000)
				            }
				        }
				        },
			            
			            rangeSelector : {
			                selected : 1
			            },
			
			            title : {
			                text : 'CPU'
			            },
			
			            series : [{
			                name : 'Utilized (%)',
			                data : data,
			                tooltip: {
			                    valueDecimals: 2
			                }
			            }]
			        });
			    });
			    
		</script>
	</head>
	<body>
	

     <div id="memory_chart" style="float:left"></div>
     <div id="cpu_chart" style="float:left"></div>		
	
	</body>
</html>
