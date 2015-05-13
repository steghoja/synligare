<?xml version="1.0" encoding="UTF-8"?>

<!-- XSL transformation from ygraphml 1.1 to SGraphML v0.6 -->

<!-- Xalan 2.7.1 supports only XSLT 1.0 -->

<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://graphml.graphdrawing.org/xmlns"
	xmlns:g="http://graphml.graphdrawing.org/xmlns" xmlns:y="http://www.yworks.com/xml/graphml"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:yed="http://www.yworks.com/xml/yed/3"
	xmlns:s="http://www.synligare.eu/sgraphml" exclude-result-prefixes="y g yed">

	<xsl:output method="xml" indent="yes" />

	<!-- For graphml tag, make sure we get the right namespaces and schemalocation -->
	<xsl:template match="/g:graphml">
		<xsl:text>&#10;</xsl:text>
		<graphml
			xsi:schemaLocation="http://graphml.graphdrawing.org/xmlns http://www.synligare.eu/sgraphml/0.6/sgraphml.xsd">
			<xsl:text>&#10;&#10;</xsl:text>
			<xsl:call-template name="addKeys" />
			<xsl:apply-templates />
		</graphml>
	</xsl:template>

	<!-- keys in ygraphml file, they have different numbers if the file was 
		created by yed or s2y transform -->
	<xsl:variable name="nodegraphicsID"
		select="/g:graphml/g:key[@yfiles.type='nodegraphics']/@id" />
	<xsl:variable name="edgegraphicsID"
		select="/g:graphml/g:key[@yfiles.type='edgegraphics']/@id" />
	<xsl:variable name="resourcesID"
		select="/g:graphml/g:key[@yfiles.type='resources']/@id" />
	<xsl:variable name="nodeURLID"
		select="/g:graphml/g:key[@for='node' and @attr.name='url' and @attr.type='string']/@id" />
	<xsl:variable name="nodeElementTypeID"
		select="/g:graphml/g:key[@for='node' and @attr.name='elementType' and @attr.type='string']/@id" />
	<xsl:variable name="edgeURLID"
		select="/g:graphml/g:key[@for='edge' and @attr.name='url' and @attr.type='string']/@id" />
	<xsl:variable name="edgeElementTypeID"
		select="/g:graphml/g:key[@for='edge' and @attr.name='elementType' and @attr.type='string']/@id" />

	<!-- keys in sgraphml fiule -->
	<xsl:variable name="s_nodegraphicsID" select="'d0'" />
	<xsl:variable name="s_edgegraphicsID" select="'d1'" />
	<xsl:variable name="s_resourcesID" select="'d2'" />
	<xsl:variable name="s_nodeURLID" select="'d10'" />
	<xsl:variable name="s_nodeElementTypeID" select="'d11'" />
	<xsl:variable name="s_edgeURLID" select="'d12'" />
	<xsl:variable name="s_edgeElementTypeID" select="'d13'" />

	<xsl:template name="addKeys">

		<xsl:element name="key">
			<xsl:attribute name="id"><xsl:value-of select="$s_nodegraphicsID" /></xsl:attribute>
			<xsl:attribute name="for">node</xsl:attribute>
			<xsl:attribute name="sgraphml.type">nodegraphics</xsl:attribute>
		</xsl:element>

		<xsl:element name="key">
			<xsl:attribute name="id"><xsl:value-of select="$s_edgegraphicsID" /></xsl:attribute>
			<xsl:attribute name="for">edge</xsl:attribute>
			<xsl:attribute name="sgraphml.type">edgegraphics</xsl:attribute>
		</xsl:element>

		<xsl:element name="key">
			<xsl:attribute name="id"><xsl:value-of select="$s_resourcesID" /></xsl:attribute>
			<xsl:attribute name="for">graphml</xsl:attribute>
			<xsl:attribute name="sgraphml.type">resources</xsl:attribute>
		</xsl:element>
	</xsl:template>
   
	<xsl:template match="g:graph">
		<xsl:element name="graph">
			<xsl:copy-of select="@*" />
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<xsl:template match="g:node">
		
		
 		<xsl:variable name="elementType" select="./g:data[@key=$nodeElementTypeID]/text()"/>
   		<xsl:variable name="id"          select="./g:data[@key=$nodeURLID]/text()"/> 
		
		<!--  strip whitespace to be able to add as attribute -->
		<xsl:variable name="cleanElementType" select = "normalize-space($elementType)"/>
		<xsl:variable name="cleanId" select = "normalize-space($id)"/>

		<xsl:element name="node">
			<xsl:attribute name="elementType"><xsl:value-of select="$cleanElementType" /></xsl:attribute>
			<xsl:attribute name="id"><xsl:value-of select="$cleanId" /></xsl:attribute>
						<xsl:apply-templates />
		</xsl:element>
	</xsl:template>


	<!-- data nodes -->
	<xsl:template match="g:data">
			  <xsl:choose>
			      <xsl:when test="./@key=$nodegraphicsID">
			      		<!-- build the data node without namespaces -->
						<xsl:element name="data">
							<xsl:attribute name="key"><xsl:value-of select="$s_nodegraphicsID" /></xsl:attribute>
					      	<xsl:apply-templates />
						</xsl:element>
			      </xsl:when>
		
			      <xsl:when test="./@key=$edgegraphicsID">
	     				<xsl:element name="data">
							<xsl:attribute name="key"><xsl:value-of select="$s_edgegraphicsID" /></xsl:attribute>
					      	<xsl:apply-templates />
						</xsl:element>
			      </xsl:when>
			      
			      <xsl:when test="./@key=$resourcesID">
	     				<xsl:element name="data">
							<xsl:attribute name="key"><xsl:value-of select="$s_resourcesID" /></xsl:attribute>
					      	<xsl:apply-templates />
						</xsl:element>
			      </xsl:when>

				  <!--  do nothing for the other keys, their content is used elsewhere -->
			      <xsl:when test="@key=$nodeURLID"/>
			      <xsl:when test="@key=$nodeElementTypeID"/>
			      <xsl:when test="@key=$edgeURLID"/>
			      <xsl:when test="@key=$edgeElementTypeID"/>
			   </xsl:choose>

	</xsl:template>


	<xsl:template match="y:GroupNode">
		<xsl:choose>
			<xsl:when test="./y:State/@closed = 'true'">
				<!-- skip the closed version -->
			</xsl:when>
			<xsl:otherwise>
				<!-- the opened version of the group node -->
				<xsl:element name="s:GroupNode">
					<xsl:apply-templates />
				</xsl:element>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>


	<xsl:template match="y:ProxyAutoBoundsNode">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="y:Realizers">
		<xsl:apply-templates />
	</xsl:template>

	<!-- Shapenodes are mapped to PortNodes or ShapeNodes -->
	<xsl:template match="y:ShapeNode">

		<xsl:variable name="portTypeList" select="'FailureOutPort FaultInPort FunctionClientServerPort FunctionFlowPort FunctionPowerPort CommunicationHardwarePin IOHardwarePin PowerHardwarePin HardwarePort'" />
		<xsl:variable name="elementType" select="../../g:data[@key=$nodeElementTypeID]/text()" />
		<xsl:variable name="cleanElementType" select = "normalize-space($elementType)"/>

		  <xsl:choose>
			<xsl:when test="contains($portTypeList, $cleanElementType)">
				<xsl:element name="s:PortNode">
					<xsl:apply-templates mode = "portnode"/>
				</xsl:element>
			</xsl:when>
			<xsl:otherwise>
				<xsl:element name="s:ShapeNode">
					<xsl:apply-templates />
				</xsl:element>
			</xsl:otherwise>
		  </xsl:choose>
	</xsl:template>

	<!-- Geometry -->
	<xsl:template match="y:Geometry">
		<xsl:element name="s:Geometry">
			<xsl:copy-of select="@*" />
		</xsl:element>
	</xsl:template>

	<xsl:template match="y:Geometry" mode = "portnode">
		<xsl:apply-templates select="." />
	</xsl:template>



	<!-- Fill - skip transparent attribute -->
	<xsl:template match="y:Fill">
		<xsl:element name="s:Fill">
			<xsl:copy-of select="@*[name()!='transparent']" />
		</xsl:element>
	</xsl:template>

	<!--  For portnode mode, just reuse template above (which otherwise would not match since theres no mode="#all# in XSLT 1.0) -->
	<xsl:template match="y:Fill" mode = "portnode">
		<xsl:apply-templates select="." />
	</xsl:template>


	<!-- BorderStyle- -->
	<xsl:template match="y:BorderStyle">
		<xsl:element name="s:BorderStyle">
			<xsl:copy-of select="@*" />
		</xsl:element>
	</xsl:template>
	<xsl:template match="y:BorderStyle" mode = "portnode">
		<xsl:apply-templates select="." />
	</xsl:template>
	


	<!-- NodeLabel -->
	<xsl:template match="y:NodeLabel">
		<xsl:element name="s:NodeLabel">

			<!-- modelName -->
			<xsl:if test="@modelName!='internal'">
				<xsl:comment>
					Unsupported modelName, Defaulting to internal.
				</xsl:comment>
			</xsl:if>

			<!-- Transform modelPosition to placement -->
			<xsl:choose>
				<xsl:when test="@modelPosition='c'">
					<xsl:attribute name="placement">center</xsl:attribute>
				</xsl:when>
				<xsl:when test="@modelPosition='t'">
					<xsl:attribute name="placement">top</xsl:attribute>
				</xsl:when>
				<xsl:when test="@modelPosition='b'">
					<xsl:attribute name="placement">bottom</xsl:attribute>
				</xsl:when>
				<xsl:when test="@modelPosition='l'">
					<xsl:attribute name="placement">left</xsl:attribute>
				</xsl:when>
				<xsl:when test="@modelPosition='r'">
					<xsl:attribute name="placement">right</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<!-- 	Unsupported modelposition, Defaulting. -->
					<xsl:attribute name="placement">top</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>


			<!-- copy font attributes -->
			<xsl:copy-of
				select="@*[name()='fontFamily' or name()='fontSize' or name()='textColor']" />

			<!-- copy size rectangle attributes -->
			<xsl:copy-of
				select="@*[name()='x' or name()='y' or name()='width' or name()='height']" />

			<!-- copy visible attributes -->
			<xsl:copy-of select="@*[name()='visible']" />

			<!-- copy position attributes -->
			<xsl:copy-of
				select="@*[name()='horizontalTextPosition' or name()='verticalTextPosition' ]" />

			<!-- copy icon attributes -->
			<xsl:copy-of select="@*[name()='iconData' or name()='iconTextGap' ]" />



			<!-- and copy the label text -->
			<xsl:value-of select="." />
		</xsl:element>
	</xsl:template>

	<xsl:template match="y:NodeLabel" mode = "portnode">
		<xsl:apply-templates select="." />
	</xsl:template>


	<!-- Shape: PortNodes  -->
	<xsl:template match="y:Shape" mode="portnode">
		<!--  there is no shape element for portnodes, they are always rectangular -->
	</xsl:template>

<!--  Shape: GroupNode/ShapeNode => map types to cutrectangle since ygraphml has no cutrectangle -->
	<xsl:template match="y:Shape">
	<xsl:element name="s:Shape">
		<xsl:variable name="elementType">
			<xsl:choose>
				<xsl:when test="parent::y:GroupNode">
					<xsl:value-of select="../../../../../g:data[@key=$nodeElementTypeID]/text()" />
				</xsl:when>
				<xsl:otherwise>
					<!-- shapeNode -->
					<xsl:value-of select="../../../g:data[@key=$nodeElementTypeID]/text()" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="typeList"
			select="'DesignFunctionType ErrorModelType Actuator LocalDeviceManager Node Sensor HardwareComponentType BasicSoftwareFunction HardwareFunctionType AnalysisFunctionType'" />
		<xsl:variable name="cleanElementType" select = "normalize-space($elementType)"/>

		<xsl:choose>
			<xsl:when test="contains($typeList, $cleanElementType)">
				<xsl:attribute name="type">cutrectangle</xsl:attribute>
			</xsl:when>

			<xsl:when test="@type='rectangle' or @type='triangle' or @type='roundrectangle'">
				<xsl:copy-of select="@*" />
			</xsl:when>

			<xsl:otherwise>
				<!-- unsupported shape type, defaulting to rectangle.-->
				<xsl:attribute name="type">rectangle</xsl:attribute>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:element>
	</xsl:template>


<!-- jag har glömt fixa source & target -->
<!-- elementtype fungerar inte -->
	<xsl:template match="g:edge">
	
		<xsl:variable name="elementType" select="./g:data[@key=$edgeElementTypeID]/text()"/>
   		<xsl:variable name="id"          select="./g:data[@key=$edgeURLID]/text()"/> 

		<!--  strip whitespace to be able to add as attribute -->
		<xsl:variable name="cleanElementType" select = "normalize-space($elementType)"/>
		<xsl:variable name="cleanId" select = "normalize-space($id)"/>
	
		<xsl:element name="edge">
			<xsl:attribute name="elementType"><xsl:value-of	select="$cleanElementType" /></xsl:attribute>
			<xsl:attribute name="id"><xsl:value-of select="$cleanId" /></xsl:attribute>
			
			<xsl:copy-of select="@source"/>
			<xsl:copy-of select="@target"/>
			
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<xsl:template match="y:PolyLineEdge">
		<xsl:element name="s:PolyLineEdge">
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<xsl:template match="y:Path">
		<xsl:element name="s:Path">
			<xsl:copy-of select="@*" />
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<xsl:template match="y:Point">
		<xsl:element name="s:Point">
			<xsl:copy-of select="@*" />
		</xsl:element>
	</xsl:template>


	<xsl:template match="y:LineStyle">
		<xsl:element name="s:LineStyle">
			<xsl:copy-of select="@*" />
		</xsl:element>
	</xsl:template>

	<xsl:template match="y:Arrows">
		<xsl:element name="s:Arrows">
			<xsl:copy-of select="@*" />
		</xsl:element>
	</xsl:template>

	<xsl:template match="y:EdgeLabel">
		<xsl:element name="s:EdgeLabel">

			<!-- copy font attributes -->
			<xsl:copy-of
				select="@*[name()='fontFamily' or name()='fontSize' or name()='textColor' ]" />

			<!-- copy visible attribute -->
			<xsl:copy-of select="@*[name()='visible']" />

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


	<xsl:template match="y:Resources">
		<xsl:element name="s:Resources">
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<!-- Change type of resource from java.awt.image.BufferedImage -> image -->
	<xsl:template match="y:Resource">
		<xsl:element name="s:Resource">
			<xsl:copy-of select="@*[name()!='type']" />
			<xsl:if test="@type">
				<xsl:choose>
					<xsl:when test="@type='java.awt.image.BufferedImage'">
						<xsl:attribute name="type">image</xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:copy-of select="@type" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:if>
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<xsl:template match="yed:ScaledIcon">
		<xsl:element name="s:ScaledIcon">
			<xsl:copy-of select="@*" />
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<xsl:template match="yed:ImageIcon">
		<xsl:element name="s:ImageIcon">
			<xsl:copy-of select="@*" />
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

</xsl:transform>