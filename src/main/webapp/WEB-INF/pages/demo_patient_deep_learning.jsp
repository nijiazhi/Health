<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html class="ocks-org do-not-copy">
<meta charset="utf-8">
<title>病人深度学习过程</title>
<style>

@import url(js/patient_metric_learning/style1.css?aea6f0a);

#chart {
  margin-left: -40px;
  height: 506px;
}

text {
  font: 10px sans-serif;
}

.dot {
  stroke: #000;
}

.axis path, .axis line {
  fill: none;
  stroke: #000;
  shape-rendering: crispEdges;
}

.label {
  fill: #777;
}

.year.label {
  font: 500 196px "Helvetica Neue";
  fill: #ddd;
}

.year.label.active {
  fill: #aaa;
}

.overlay {
  fill: none;
  pointer-events: all;
  cursor: ew-resize;
}

</style>

<header>
  <!-- <aside>2017</aside> -->
 <!--  <a href="../" rel="corporation">中国科学院软件研究所 软件工程技术研发中心</a> -->
</header>

<div style="margin-left:200px">
	<h1>深度学习示意图<small > --- 病人相似度</small></h1>
	<p id="chart"></p>
	
<!-- 	<aside>Number of steps of training through time.</aside>-->	
	<p><strong>使用病人数据集共有10类。用10-5-5-2结构的网络结合Data Summarization算法将数据降到2维，展示了训练过程中数据变换的过程。</strong></p>
<!-- 	<p><strong>这是一个深度度量学习的demo，我们使用了一个toy数据集，一共有10个类。用10-5-5-2结构的网络结合Data Summarization算法将数据降到2维，展示了训练过程中数据变换的过程。</strong></p>-->
	<footer>
	 <!--  <aside>2017</aside> -->
	  <a href="../" rel="corporation">中国科学院软件研究所</a>
	</footer>
</div>

<script src="resources/js/patient_metric_learning/d3.v3.min.js" charset="utf-8"></script></script>
<script async src="resources/js/patient_metric_learning/analytics.js"></script>


<script>

// Various accessors that specify the four dimensions of data to visualize.
function x(d) { return d.x; }
function y(d) { return d.y; }
function radius(d) { return d.population; }
function color(d) { return d.region; }
function key(d) { return d.name; }

// Chart dimensions.
var margin = {top: 19.5, right: 19.5, bottom: 19.5, left: 39.5},
    width = 960 - margin.right,
    height = 500 - margin.top - margin.bottom;

// Various scales. These domains make assumptions of data, naturally.
var xScale = d3.scale.linear().domain([0,1800]).range([0, width]),
    yScale = d3.scale.linear().domain([0, 1200]).range([height, 0]),
    radiusScale = d3.scale.sqrt().domain([0, 40000]).range([0, 120]),
    colorScale = d3.scale.category10();

// The x & y axes.
var xAxis = d3.svg.axis().orient("bottom").scale(xScale).ticks(12, d3.format(",d")),
    yAxis = d3.svg.axis().scale(yScale).orient("left");

// Create the SVG container and set the origin.
var svg = d3.select("#chart").append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
  .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

// Add the x-axis.
svg.append("g")
    .attr("class", "x axis")
    .attr("transform", "translate(0," + height + ")")
    .call(xAxis);

// Add the y-axis.
svg.append("g")
    .attr("class", "y axis")
    .call(yAxis);

// Add an x-axis label.
svg.append("text")
    .attr("class", "x label")
    .attr("text-anchor", "end")
    .attr("x", width)
    .attr("y", height - 6)
    .text("first dimension of the data");

// Add a y-axis label.
svg.append("text")
    .attr("class", "y label")
    .attr("text-anchor", "end")
    .attr("y", 6)
    .attr("dy", ".75em")
    .attr("transform", "rotate(-90)")
    .text("second dimension of the data");

// Add the year label; the value is set on transition.
var label = svg.append("text")
    .attr("class", "year label")
    .attr("text-anchor", "end")
    .attr("y", height - 24)
    .attr("x", width)
    .text(0);




// Load the data.
d3.json("resources/js/patient_metric_learning/data_deep_learning.json", function(nations) {

  // A bisector since many nation's data is sparsely-defined.
  var bisect = d3.bisector(function(d) { return d[0]; });

  // Add a dot per nation. Initialize the data at 1800, and set the colors.
  var dot = svg.append("g")
      .attr("class", "dots")
    .selectAll(".dot")
      .data(interpolateData(0))
    .enter().append("circle")
      .attr("class", "dot")
      .style("fill", function(d) { return colorScale(color(d)); })
      .call(position)
      .sort(order);

  // Add a title.
  dot.append("title")
      .text(function(d) { return d.name; });

  // Add an overlay for the year label.
  var box = label.node().getBBox();

  var overlay = svg.append("rect")
        .attr("class", "overlay")
        .attr("x", box.x)
        .attr("y", box.y)
        .attr("width", box.width)
        .attr("height", box.height)
        .on("mouseover", enableInteraction);

  // Start a transition that interpolates the data based on year.
  svg.transition()
      .duration(10000)
      .ease("linear")
      .tween("year", tweenYear)
      .each("end", enableInteraction);

  // Positions the dots based on data.
  function position(dot) {
    dot .attr("cx", function(d) { return xScale(x(d)); })
        .attr("cy", function(d) { return yScale(y(d)); })
        .attr("r", function(d) { return radiusScale(radius(d)); });
  }

  // Defines a sort order so that the smallest dots are drawn on top.
  function order(a, b) {
    return radius(b) - radius(a);
  }

  // After the transition finishes, you can mouseover to change the year.
  function enableInteraction() {
    var yearScale = d3.scale.linear()
        .domain([0, 6000])
        .range([box.x + 10, box.x + box.width - 10])
        .clamp(true);

    // Cancel the current transition, if any.
    svg.transition().duration(0);

    overlay
        .on("mouseover", mouseover)
        .on("mouseout", mouseout)
        .on("mousemove", mousemove)
        .on("touchmove", mousemove);

    function mouseover() {
      label.classed("active", true);
    }

    function mouseout() {
      label.classed("active", false);
    }

    function mousemove() {
      displayYear(yearScale.invert(d3.mouse(this)[0]));
    }
  }

  // Tweens the entire chart by first tweening the year, and then the data.
  // For the interpolated data, the dots and label are redrawn.
  function tweenYear() {
    var year = d3.interpolateNumber(0, 6000);
    return function(t) { displayYear(year(t)); };
  }

  // Updates the display to show the specified year.
  function displayYear(year) {
    dot.data(interpolateData(year), key).call(position).sort(order);
    label.text(Math.round(year));
  }

  // Interpolates the dataset for the given (fractional) year.
  function interpolateData(year) {
    return nations.map(function(d) {
      return {
        name: d.name,
        region: d.region,
        x: interpolateValues(d.x, year),
        population: interpolateValues(d.population, year),
        y: interpolateValues(d.y, year)
      };
    });
  }

  // Finds (and possibly interpolates) the value for the specified year.
  function interpolateValues(values, year) {
    var i = bisect.left(values, year, 0, values.length - 1),
        a = values[i];
    if (i > 0) {
      var b = values[i - 1],
          t = (year - a[0]) / (b[0] - a[0]);
      return a[1] * (1 - t) + b[1] * t;
    }
    return a[1];
  }
});
//结束nation数据-函数
</script>

<script>
GoogleAnalyticsObject = "ga", ga = function() { ga.q.push(arguments); }, ga.q = [], ga.l = +new Date;
ga("create", "UA-48272912-3", "ocks.org");
ga("send", "pageview");
</script>