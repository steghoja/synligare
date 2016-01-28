<?xml version="1.0" encoding="UTF-8"?>

<!-- XSL transformation from SGraphML v0.7 to ygraphml 1.1 -->

<!-- Xalan 2.7.1 supports only XSLT 1.0 -->

<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://graphml.graphdrawing.org/xmlns"
	xmlns:g="http://graphml.graphdrawing.org/xmlns" xmlns:y="http://www.yworks.com/xml/graphml"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:yed="http://www.yworks.com/xml/yed/3"
	xmlns:s="http://www.synligare.eu/sgraphml" exclude-result-prefixes="s g">

	<xsl:output method="xml" indent="yes" />

	<!-- For graphml tag, make sure we get the right namespaces and schemalocation -->
	<xsl:template match="/g:graphml">
		<xsl:text>&#10;</xsl:text>
		<graphml
			xsi:schemaLocation="http://graphml.graphdrawing.org/xmlns http://www.yworks.com/xml/schema/graphml/1.1/ygraphml.xsd">
			<xsl:text>&#10;&#10;</xsl:text>
			<xsl:call-template name="addKeys" />
			<xsl:apply-templates />
		</graphml>
	</xsl:template>

	<xsl:variable name="nodegraphicsID" select="'d0'" />
	<xsl:variable name="edgegraphicsID" select="'d1'" />
	<xsl:variable name="resourcesID" select="'d2'" />
	<xsl:variable name="nodeURLID" select="'d10'" />
	<xsl:variable name="nodeElementType" select="'d11'" />
	<xsl:variable name="edgeURLID" select="'d12'" />
	<xsl:variable name="edgeElementTypeID" select="'d13'" />

	<xsl:template name="addKeys">

		<xsl:element name="key">
			<xsl:attribute name="id"><xsl:value-of select="$nodegraphicsID" /></xsl:attribute>
			<xsl:attribute name="for">node</xsl:attribute>
			<xsl:attribute name="yfiles.type">nodegraphics</xsl:attribute>
		</xsl:element>

		<xsl:element name="key">
			<xsl:attribute name="id"><xsl:value-of select="$edgegraphicsID" /></xsl:attribute>
			<xsl:attribute name="for">edge</xsl:attribute>
			<xsl:attribute name="yfiles.type">edgegraphics</xsl:attribute>
		</xsl:element>

		<xsl:element name="key">
			<xsl:attribute name="id"><xsl:value-of select="$resourcesID" /></xsl:attribute>
			<xsl:attribute name="for">graphml</xsl:attribute>
			<xsl:attribute name="yfiles.type">resources</xsl:attribute>
		</xsl:element>

		<xsl:element name="key">
			<xsl:attribute name="id"><xsl:value-of select="$nodeURLID" /></xsl:attribute>
			<xsl:attribute name="for">node</xsl:attribute>
			<xsl:attribute name="attr.type">string</xsl:attribute>
			<xsl:attribute name="attr.name">url</xsl:attribute>
		</xsl:element>

		<xsl:element name="key">
			<xsl:attribute name="id"><xsl:value-of select="$nodeElementType" /></xsl:attribute>
			<xsl:attribute name="for">node</xsl:attribute>
			<xsl:attribute name="attr.type">string</xsl:attribute>
			<xsl:attribute name="attr.name">elementType</xsl:attribute>
		</xsl:element>

		<xsl:element name="key">
			<xsl:attribute name="id"><xsl:value-of select="$edgeURLID" /></xsl:attribute>
			<xsl:attribute name="for">edge</xsl:attribute>
			<xsl:attribute name="attr.type">string</xsl:attribute>
			<xsl:attribute name="attr.name">url</xsl:attribute>
		</xsl:element>

		<xsl:element name="key">
			<xsl:attribute name="id"><xsl:value-of select="$edgeElementTypeID" /></xsl:attribute>
			<xsl:attribute name="for">edge</xsl:attribute>
			<xsl:attribute name="attr.type">string</xsl:attribute>
			<xsl:attribute name="attr.name">elementType</xsl:attribute>
		</xsl:element>
	</xsl:template>



	<xsl:template match="g:graph">
		<xsl:element name="graph">
			<xsl:copy-of select="@*" />
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<xsl:template match="g:node">

		<xsl:element name="node">
			<xsl:copy-of select="@*[name()!='elementType']" />
			<!-- add yfiles.foldertype for groupnode -->
			<xsl:if test="./data[@key=$nodegraphicsID]/s:GroupNode">
				<xsl:attribute name="yfiles.foldertype">group</xsl:attribute>
			</xsl:if>

			<!-- backup path as url, since yed will replace paths with "nx:ny" format 
				upon save -->
			<xsl:element name="data">
				<xsl:attribute name="key"><xsl:value-of select="$nodeURLID" /></xsl:attribute>
				<xsl:value-of select="./@id" />
			</xsl:element>

			<!-- backup elementType for roundtrip -->
			<xsl:element name="data">
				<xsl:attribute name="key"><xsl:value-of select="$nodeElementType" /></xsl:attribute>
				<xsl:value-of select="./@elementType" />
			</xsl:element>
			<xsl:apply-templates />

		</xsl:element>
	</xsl:template>


	<xsl:template match="g:data">
		<xsl:element name="data">
			<xsl:copy-of select="@*" />
			<xsl:apply-templates />

		</xsl:element>
	</xsl:template>


	<xsl:template name="min">
		<xsl:param name="nodes" select="/.." />
		<xsl:choose>
			<xsl:when test="not($nodes)">
				NaN
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="$nodes">
					<xsl:sort data-type="number" order="ascending" />
					<xsl:if test="position( ) = 1">
						<xsl:value-of select="number(.)" />
					</xsl:if>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>


	<!-- finds the maximum value based on the sum of x and with attributes -->
	<xsl:template name="max_sumX">
		<xsl:param name="nodes" select="/.." />

		<xsl:choose>
			<xsl:when test="not($nodes)">
				NaN
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="$nodes">
					<xsl:sort data-type="number" order="descending" select="./@x + ./@width" />
					<xsl:if test="position( ) = 1">
						<xsl:value-of select="number(./@x + ./@width)" />
					</xsl:if>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<!-- tricks (: min :) ($x <= $y) * $x + ($y < $x) * $y (: max :) ($x >= 
		$y) * $x + ($y > $x) * $y -->


	<!-- finds the maximum value based on the sum of y and with height attributes -->
	<xsl:template name="max_sumY">
		<xsl:param name="nodes" select="/.." />

		<xsl:choose>
			<xsl:when test="not($nodes)">
				NaN
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="$nodes">
					<xsl:sort data-type="number" order="descending" select="./@y + ./@height" />
					<xsl:if test="position( ) = 1">
						<xsl:value-of select="number(./@y + ./@height)" />
					</xsl:if>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>


	<!-- calculates the left x-coordinate of the enclosing rectangle of the 
		portlabels -->
	<xsl:template name="minX0_portLabel">
		<xsl:param name="ports" select="/.." />
		<xsl:choose>
			<xsl:when test="not($ports/s:Geometry/@x + $ports/s:NodeLabel/@x)">
				NaN
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="$ports">
					<xsl:sort data-type="number" order="ascending"
						select="./s:Geometry/@x + ./s:NodeLabel/@x" />
					<xsl:if test="position( ) = 1">
						<xsl:value-of select="number(./s:Geometry/@x + ./s:NodeLabel/@x)" />
					</xsl:if>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<!-- calculates the upper y-coordinate of the enclosing rectangle of the 
		portlabels -->
	<xsl:template name="minY0_portLabel">
		<xsl:param name="ports" select="/.." />
		<xsl:choose>
			<xsl:when test="not($ports/s:Geometry/@y + $ports/s:NodeLabel/@y)">
				NaN
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="$ports">
					<xsl:sort data-type="number" order="ascending"
						select="./s:Geometry/@y + ./s:NodeLabel/@y" />
					<xsl:if test="position( ) = 1">
						<xsl:value-of select="number(./s:Geometry/@y + ./s:NodeLabel/@y)" />
					</xsl:if>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<!-- calculates the right x-coordinate of the enclosing rectangle of the 
		portlabels -->
	<xsl:template name="maxX1_portLabel">
		<xsl:param name="ports" select="/.." />
		<xsl:choose>
			<xsl:when
				test="not($ports/s:Geometry/@x + $ports/s:NodeLabel/@x + $ports/s:NodeLabel/@width)">
				NaN
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="$ports">
					<xsl:sort data-type="number" order="descending"
						select="./s:Geometry/@x + ./s:NodeLabel/@x + ./s:NodeLabel/@width" />
					<xsl:if test="position( ) = 1">
						<xsl:value-of
							select="number(./s:Geometry/@x + ./s:NodeLabel/@x + ./s:NodeLabel/@width)" />
					</xsl:if>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<!-- calculates the lower y-coordinate of the enclosing rectangle of the 
		portlabels -->
	<xsl:template name="maxY1_portLabel">
		<xsl:param name="ports" select="/.." />
		<xsl:choose>
			<xsl:when
				test="not($ports/s:Geometry/@y + $ports/s:NodeLabel/@y + $ports/s:NodeLabel/@height)">
				NaN
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="$ports">
					<xsl:sort data-type="number" order="descending"
						select="./s:Geometry/@y + ./s:NodeLabel/@y + ./s:NodeLabel/@height" />
					<xsl:if test="position( ) = 1">
						<xsl:value-of
							select="number(./s:Geometry/@y + ./s:NodeLabel/@y + ./s:NodeLabel/@height)" />
					</xsl:if>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<!-- data node for nodegraphics -->
	<xsl:template match="g:data[@key=$nodegraphicsID]">
		<!-- build the data node without namespaces -->
		<xsl:element name="data">
			<xsl:attribute name="key">
         	<xsl:value-of select="@key" />
	    </xsl:attribute>
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>




	<!-- Calculates the enclosing Rectangle (x0,y0) (x1,y1) that encloses the 
		portNodes & portLabels of a groupnode -->
	<xsl:template name="enclosingPortsRectangle">


		<!-- Calculate the enclosing rectangle of the port labels Since we know 
			that the portlabels enclose the ports we don't calculate any rectangle for 
			the ports themselves -->
		<xsl:variable name="ports"
			select="./../../g:graph/g:node/g:data[@key=$nodegraphicsID]/s:PortNode" />


		<!-- xa0 -->
		<xsl:variable name="xa0">
			<xsl:choose>
				<!-- are there any ports with an x coordinate defined? -->
				<xsl:when test="count($ports/s:Geometry/@x) &gt; 0">
					<xsl:call-template name="minX0_portLabel">
						<xsl:with-param name="ports" select="$ports" />
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<!-- high value not to interfer -->
					<xsl:value-of select="10000000" />
				</xsl:otherwise>
			</xsl:choose>

		</xsl:variable>

		<!-- ya0 -->
		<xsl:variable name="ya0">
			<xsl:choose>
				<!-- are there any ports with an y coordinate defined? -->
				<xsl:when test="count($ports/s:Geometry/@y) &gt; 0">
					<xsl:call-template name="minY0_portLabel">
						<xsl:with-param name="ports" select="$ports" />
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<!-- high value not to interfer -->
					<xsl:value-of select="20000000" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>

		<!-- xa1 -->
		<xsl:variable name="xa1">
			<xsl:choose>
				<!-- are there any ports with x & y defined? -->
				<xsl:when
					test="(count($ports/s:Geometry/@x) &gt; 0) and (count($ports/s:Geometry/@width) &gt; 0)">
					<xsl:call-template name="maxX1_portLabel">
						<xsl:with-param name="ports" select="$ports" />
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<!-- low value not to interfer -->
					<xsl:value-of select="-20000000" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>

		<!-- ya1 -->
		<xsl:variable name="ya1">
			<xsl:choose>
				<!-- are there any ports with x & y defined? -->
				<xsl:when
					test="(count($ports/s:Geometry/@y) &gt; 0) and (count($ports/s:Geometry/@height) &gt; 0)">
					<xsl:call-template name="maxY1_portLabel">
						<xsl:with-param name="ports" select="$ports" />
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<!-- low value not to interfer -->
					<xsl:value-of select="-20000000" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>

		<!-- return absolute values -->

		<xsl:value-of select="$xa0" />
		<xsl:text>;</xsl:text>
		<xsl:value-of select="$ya0" />
		<xsl:text>;</xsl:text>
		<xsl:value-of select="$xa1" />
		<xsl:text>;</xsl:text>
		<xsl:value-of select="$ya1" />
	</xsl:template>


	<!-- Calculates the enclosing Rectangle (x0,y0) (x1,y1) that encloses the 
		ShapeNodes & GroupNodes of a groupnode -->
	<xsl:template name="enclosingOtherContentRectangle">
		<!-- ShapeNodes & GroupNodes -->
		<xsl:variable name="otherContent"
			select="./../../g:graph/g:node/g:data[@key=$nodegraphicsID]/s:ShapeNode/s:Geometry | ./../../g:graph/g:node/g:data[@key=$nodegraphicsID]/s:GroupNode/s:Geometry" />

		<!-- xsg0 -->
		<xsl:variable name="xsg0">
			<xsl:choose>
				<xsl:when test="count($otherContent/@x) &gt; 0">
					<xsl:call-template name="min">
						<xsl:with-param name="nodes" select="$otherContent/@x" />
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<!-- high value not to interfere -->
					<xsl:value-of select="10000000" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>

		<!-- ysg0 -->
		<xsl:variable name="ysg0">
			<xsl:choose>
				<xsl:when test="count($otherContent/@y) &gt; 0">
					<xsl:call-template name="min">
						<xsl:with-param name="nodes" select="$otherContent/@y" />
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<!-- high value not to interfere -->
					<xsl:value-of select="10000000" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>

		<!-- xsg1 -->
		<xsl:variable name="xsg1">
			<xsl:choose>
				<xsl:when test="count($otherContent/@x) &gt; 0">
					<xsl:call-template name="max_sumX">
						<xsl:with-param name="nodes" select="$otherContent" />
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<!-- low value not to interfere -->
					<xsl:value-of select="-10000000" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>

		<!-- ysg1 -->
		<xsl:variable name="ysg1">
			<xsl:choose>
				<xsl:when test="count($otherContent/@y) &gt; 0">
					<xsl:call-template name="max_sumY">
						<xsl:with-param name="nodes" select="$otherContent" />
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<!-- low value not to interfere -->
					<xsl:value-of select="-10000000" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>

		<!-- return absolute values -->

		<xsl:value-of select="$xsg0" />
		<xsl:text>;</xsl:text>
		<xsl:value-of select="$ysg0" />
		<xsl:text>;</xsl:text>
		<xsl:value-of select="$xsg1" />
		<xsl:text>;</xsl:text>
		<xsl:value-of select="$ysg1" />
	</xsl:template>



	<!-- GroupNode Since the groupnode has autobounds, we need to calculate 
		the BorderInsets This is based on the Rectangle (x0,y0) (x1,y1) that encloses 
		the contents of the group node Contents of GroupNode: PortNode(s) Rectangle 
		absolute coordinates PortNode(s) NodeLabel coordinates relative to port node 
		ShapeNode(s) Rectangle absolute coordinates GroupNode(s) Rectangle absolute 
		coordinates The groupNode label is outside, i.e. the topMargin is measured 
		up to the label (if top label). Current implementation skips shapenodes && 
		groupnodes in groupnodes -->

	<xsl:template match="s:GroupNode">

		<xsl:element name="y:ProxyAutoBoundsNode">
			<xsl:element name="y:Realizers">
				<xsl:attribute name="active">0</xsl:attribute>

				<!-- the open group node -->
				<xsl:element name="y:GroupNode">
					<xsl:apply-templates />
					<xsl:element name="y:State">
						<xsl:attribute name="closed">false</xsl:attribute>
						<xsl:attribute name="closedHeight">50</xsl:attribute>
						<xsl:attribute name="closedWidth">50</xsl:attribute>
						<xsl:attribute name="innerGraphDisplayEnabled">false</xsl:attribute>
					</xsl:element>
					<y:Insets bottom="0" bottomF="0.0" left="0" leftF="0.0"
						right="0" rightF="0.0" top="0" topF="0.0" />

					<!-- Find enclosing rectangle of Ports -->
					<xsl:variable name="enclosingPortRectangle">
						<xsl:call-template name="enclosingPortsRectangle"></xsl:call-template>
					</xsl:variable>

					<!-- result Tree Fragments - just a string: the perils of xslt 1.0 -->
					<xsl:variable name="x0">
						<xsl:value-of select="substring-before($enclosingPortRectangle, ';')" />
					</xsl:variable>
					<xsl:variable name="y0">
						<xsl:value-of
							select="substring-before(substring-after ($enclosingPortRectangle , ';'), ';')" />
					</xsl:variable>
					<xsl:variable name="x1">
						<xsl:value-of
							select="substring-before(substring-after ( substring-after ($enclosingPortRectangle,  ';'),  ';') , ';')" />
					</xsl:variable>
					<xsl:variable name="y1">
						<xsl:value-of
							select="substring-after(substring-after ( substring-after ($enclosingPortRectangle,  ';'),  ';') , ';')" />
					</xsl:variable>

					<!-- Find enclosing rectangle of ShapeNodes & GroupNodes -->
					<xsl:variable name="enclosingOtherContentRectangle">
						<xsl:call-template name="enclosingOtherContentRectangle"></xsl:call-template>
					</xsl:variable>

					<!-- result Tree Fragments - just a string: the perils of xslt 1.0 -->
					<xsl:variable name="xo0">
						<xsl:value-of
							select="substring-before($enclosingOtherContentRectangle, ';')" />
					</xsl:variable>
					<xsl:variable name="yo0">
						<xsl:value-of
							select="substring-before(substring-after ($enclosingOtherContentRectangle , ';'), ';')" />
					</xsl:variable>
					<xsl:variable name="xo1">
						<xsl:value-of
							select="substring-before(substring-after ( substring-after ($enclosingOtherContentRectangle,  ';'),  ';') , ';')" />
					</xsl:variable>
					<xsl:variable name="yo1">
						<xsl:value-of
							select="substring-after(substring-after ( substring-after ($enclosingOtherContentRectangle,  ';'),  ';') , ';')" />
					</xsl:variable>


					<!-- find the enclosing rectangle (xe0, ye0, xe1, ye1) of these 2 rectangles -->
					<xsl:variable name="xe0">
						<xsl:choose>
							<xsl:when test="$x0 &lt; $xo0 ">
								<xsl:value-of select="$x0" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="$xo0" />
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>

					<xsl:variable name="ye0">
						<xsl:choose>
							<xsl:when test="$y0 &lt; $yo0 ">
								<xsl:value-of select="$y0" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="$yo0" />
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>

					<xsl:variable name="xe1">
						<xsl:choose>
							<xsl:when test="$x1 &gt; $xo1 ">
								<xsl:value-of select="$x1" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="$xo1" />
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>

					<xsl:variable name="ye1">
						<xsl:choose>
							<xsl:when test="$y1 &gt; $yo1 ">
								<xsl:value-of select="$y1" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="$yo1" />
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>


					<!-- group node rectangle: (gx0, gy0), (gx1, gy1) -->
					<xsl:variable name="gx0">
						<xsl:value-of select="./s:Geometry/@x" />
					</xsl:variable>
					<xsl:variable name="gy0">
						<xsl:value-of select="./s:Geometry/@y" />
					</xsl:variable>
					<xsl:variable name="gx1">
						<xsl:value-of select="./s:Geometry/@x + ./s:Geometry/@width" />
					</xsl:variable>
					<xsl:variable name="gy1">
						<xsl:value-of select="./s:Geometry/@y + ./s:Geometry/@height" />
					</xsl:variable>


					<!-- calculate margins && make sure at least zero -->
					<xsl:variable name="bottomF">
						<xsl:choose>
							<xsl:when test="($gy1 - $ye1) &lt; 0">
								<xsl:value-of select="0" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="$gy1 - $ye1" />
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>

					<xsl:variable name="leftF">
						<xsl:choose>
							<xsl:when test="($xe0 - $gx0) &lt; 0">
								<xsl:value-of select="0" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="$xe0 - $gx0" />
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>

					<xsl:variable name="rightF">
						<xsl:choose>
							<xsl:when test="($gx1 - $xe1) &lt; 0">
								<xsl:value-of select="0" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="$gx1 - $xe1" />
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>

					<xsl:variable name="groupNodeLabel" select="./s:NodeLabel" />

					<!-- When the label is on the top row, the height of it is not part 
						of topF margin -->
					<xsl:variable name="topF">
						<xsl:choose>
							<xsl:when test="($ye0 - $gy0 - $groupNodeLabel/@height) &lt; 0">
								<xsl:value-of select="0" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="$ye0 - $gy0 - $groupNodeLabel/@height" />
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>

					<xsl:element name="y:BorderInsets">
						<xsl:attribute name="bottomF"><xsl:value-of
							select="$bottomF" /></xsl:attribute>
						<xsl:attribute name="bottom"><xsl:value-of
							select="round($bottomF)" /></xsl:attribute>
						<xsl:attribute name="leftF"><xsl:value-of
							select="$leftF" /></xsl:attribute>
						<xsl:attribute name="left"><xsl:value-of
							select="round($leftF)" /></xsl:attribute>
						<xsl:attribute name="rightF"><xsl:value-of
							select="$rightF" /></xsl:attribute>
						<xsl:attribute name="right"><xsl:value-of
							select="round($rightF)" /></xsl:attribute>
						<xsl:attribute name="topF"><xsl:value-of
							select="$topF" /></xsl:attribute>
						<xsl:attribute name="top"><xsl:value-of
							select="round($topF)" /></xsl:attribute>
					</xsl:element>
				</xsl:element>

				<!-- the closed group node -->
				<xsl:element name="y:GroupNode">
					<xsl:apply-templates mode="closed-group-node" />
					<xsl:element name="y:State">
						<xsl:attribute name="closed">true</xsl:attribute>
						<xsl:attribute name="closedHeight">50</xsl:attribute>
						<xsl:attribute name="closedWidth">50</xsl:attribute>
						<xsl:attribute name="innerGraphDisplayEnabled">false</xsl:attribute>
					</xsl:element>
					<y:Insets bottom="0" bottomF="0.0" left="0" leftF="0.0"
						right="0" rightF="0.0" top="0" topF="0.0" />
					<y:BorderInsets bottom="0" bottomF="0" left="0"
						leftF="0.0" right="0" rightF="0.0" top="0" topF="0" />
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>

	<!-- Portnode -->
	<xsl:template match="s:PortNode">
		<xsl:element name="y:ShapeNode">
			<xsl:apply-templates />
			<xsl:element name="y:Shape">
				<xsl:attribute name="type">rectangle</xsl:attribute>
			</xsl:element>
		</xsl:element>
	</xsl:template>


	<!-- Shapenode -->
	<xsl:template match="s:ShapeNode">
		<xsl:element name="y:ShapeNode">
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<!-- Geometry -->
	<xsl:template match="s:Geometry">
		<xsl:element name="y:Geometry">
			<xsl:copy-of select="@*" />
		</xsl:element>
	</xsl:template>

	<!-- Geometry -->
	<xsl:template match="s:Geometry" mode="closed-group-node">
		<xsl:element name="y:Geometry">
			<xsl:copy-of select="@x" />
			<xsl:copy-of select="@y" />
			<xsl:attribute name="width">50</xsl:attribute>
			<xsl:attribute name="height">50</xsl:attribute>
		</xsl:element>
	</xsl:template>



	<!-- Fill - add transparent attribute -->
	<xsl:template match="s:Fill">
		<xsl:element name="y:Fill">
			<xsl:copy-of select="@*" />
			<xsl:attribute name="transparent">false</xsl:attribute>
		</xsl:element>
	</xsl:template>

	<!-- In XSLT 1.0, mode="mode1 mode2" is not allowed, so we do this instead -->
	<xsl:template match="s:Fill" mode="closed-group-node">
		<xsl:apply-templates select="." />
	</xsl:template>


	<!-- BorderStyle- -->
	<xsl:template match="s:BorderStyle">
		<xsl:element name="y:BorderStyle">
			<xsl:copy-of select="@*" />
		</xsl:element>
	</xsl:template>
	<xsl:template match="s:BorderStyle" mode="closed-group-node">
		<xsl:apply-templates select="." />
	</xsl:template>


	<!-- NodeLabel -->
	<xsl:template match="s:NodeLabel">
		<xsl:element name="y:NodeLabel">

			<!-- add the alignment attribute, unclear how it works :-) -->
			<xsl:attribute name="alignment">left</xsl:attribute>

			<!-- copy all attributes except placement -->
			<xsl:copy-of select="@*[name()!='placement']" />

			<xsl:attribute name="modelName">internal</xsl:attribute>
			<!-- Transform placement to modelPosition -->
			<xsl:choose>
				<xsl:when test="@placement='center'">
					<xsl:attribute name="modelPosition">c</xsl:attribute>
				</xsl:when>
				<xsl:when test="@placement='top'">
					<xsl:attribute name="modelPosition">t</xsl:attribute>
				</xsl:when>
				<xsl:when test="@placement='bottom'">
					<xsl:attribute name="modelPosition">b</xsl:attribute>
				</xsl:when>
				<xsl:when test="@placement='left'">
					<xsl:attribute name="modelPosition">l</xsl:attribute>
				</xsl:when>
				<xsl:when test="@placement='right'">
					<xsl:attribute name="modelPosition">r</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<!--  Unsupported placement, Defaulting. -->
					<xsl:attribute name="modelPosition">t</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>

			<xsl:attribute name="autoSizePolicy">content</xsl:attribute>
			<xsl:attribute name="borderDistance">0.0</xsl:attribute>
			<xsl:attribute name="fontStyle">plain</xsl:attribute>
			<xsl:attribute name="hasLineColor">false</xsl:attribute>
			<xsl:attribute name="hasBackgroundColor">false</xsl:attribute>
			<xsl:attribute name="leftInset">0</xsl:attribute>
			<xsl:attribute name="rightInset">0</xsl:attribute>
			<xsl:attribute name="topInset">0</xsl:attribute>
			<xsl:attribute name="bottomInset">0</xsl:attribute>

			<xsl:value-of select="." />
		</xsl:element>
	</xsl:template>
	<xsl:template match="s:NodeLabel" mode="closed-group-node">
		<xsl:apply-templates select="." />
	</xsl:template>


	<!-- Shape: map cutrectangle to rectangle since ygraphml has no cutrectangle -->
	<xsl:template match="s:Shape">
		<xsl:element name="y:Shape">
			<xsl:choose>
				<xsl:when test="@type='cutrectangle'">
					<xsl:attribute name="type">rectangle</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:copy-of select="@type" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
	</xsl:template>

	<xsl:template match="g:edge">
		<xsl:element name="edge">
			<xsl:copy-of select="@*[name()!='elementType']" />
			
			<!-- backup path as url, since yed will replace paths with "nx:ny" format 
				upon save -->
			<xsl:element name="data">
				<xsl:attribute name="key"><xsl:value-of select="$edgeURLID" /></xsl:attribute>
				<xsl:value-of select="./@id" />
			</xsl:element>

			<!-- backup elementType for roundtrip -->
			<xsl:element name="data">
				<xsl:attribute name="key"><xsl:value-of select="$edgeElementTypeID" /></xsl:attribute>
				<xsl:value-of select="./@elementType" />
			</xsl:element>

			<xsl:apply-templates />

		</xsl:element>
	</xsl:template>

	<!-- data node for edgegraphics -->
	<xsl:template match="g:data[@key=$edgegraphicsID]">
		<!-- build the data node without namespaces -->
		<xsl:element name="data">
			<xsl:attribute name="key">
         	<xsl:value-of select="@key" />
	     </xsl:attribute>
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<xsl:template match="s:PolyLineEdge">
		<xsl:element name="y:PolyLineEdge">
			<xsl:apply-templates />

			<xsl:element name="y:BendStyle">
				<xsl:attribute name="smoothed">false</xsl:attribute>
			</xsl:element>
		</xsl:element>
	</xsl:template>

	<xsl:template match="s:Path">
		<xsl:element name="y:Path">
			<xsl:copy-of select="@*" />
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<xsl:template match="s:Point">
		<xsl:element name="y:Point">
			<xsl:copy-of select="@*" />
		</xsl:element>
	</xsl:template>


	<xsl:template match="s:LineStyle">
		<xsl:element name="y:LineStyle">
			<xsl:copy-of select="@*" />
		</xsl:element>
	</xsl:template>

	<xsl:template match="s:Arrows">
		<xsl:element name="y:Arrows">
			<xsl:copy-of select="@*" />
		</xsl:element>
	</xsl:template>

	<xsl:template match="s:EdgeLabel">
		<xsl:element name="y:EdgeLabel">
			<xsl:copy-of select="@*" />
			<xsl:attribute name="fontStyle">plain</xsl:attribute>
			<xsl:attribute name="backgroundColor">#FFFFFF</xsl:attribute>
			<xsl:attribute name="hasLineColor">false</xsl:attribute>
			<xsl:attribute name="modelName">centered</xsl:attribute>
			<xsl:attribute name="modelPosition">center</xsl:attribute>
			<xsl:value-of select="." />
		</xsl:element>
	</xsl:template>

	<!-- data node for resources -->
	<xsl:template match="/g:graphml/g:data[@key=resourcesID]">
		<!-- build the data node without namespaces -->
		<xsl:element name="g:data">
			<xsl:attribute name="key">
         	<xsl:value-of select="$resourcesID" />
	    </xsl:attribute>
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>


	<xsl:template match="s:Resources">
		<xsl:element name="y:Resources">
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<!-- Change type of resource from image -> java.awt.image.BufferedImage -->
	<xsl:template match="s:Resource">
		<xsl:element name="y:Resource">
			<xsl:copy-of select="@*[name()!='type']" />
			<xsl:if test="@type">
				<xsl:choose>
					<xsl:when test="@type='image'">
						<xsl:attribute name="type">java.awt.image.BufferedImage</xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:copy-of select="@type" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:if>
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<xsl:template match="s:ScaledIcon">
		<xsl:element name="yed:ScaledIcon">
			<xsl:copy-of select="@*" />
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<xsl:template match="s:ImageIcon">
		<xsl:element name="yed:ImageIcon">
			<xsl:copy-of select="@*" />
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<!-- help template that copies without including namespaces, just builds 
		a new one -->
	<xsl:template match="*" mode="copy-no-namespaces">
		<xsl:element name="{local-name()}">
			<xsl:copy-of select="@*" />
			<xsl:apply-templates select="node()" mode="copy-no-namespaces" />
		</xsl:element>
	</xsl:template>
	<xsl:template match="comment()| processing-instruction()"
		mode="copy-no-namespaces">
		<xsl:copy />
	</xsl:template>

</xsl:transform>