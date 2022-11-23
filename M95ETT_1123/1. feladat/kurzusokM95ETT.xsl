<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">

        <html>
            <body>
                <h2>Kurzusok atlaga - for-each, value-of</h2>
                <table border = "4">
                    <tr bgcolor="#9acd32">
                        <th>Kurzus</th>
                        <th>Atlag</th>
                    </tr>

                    <xsl:for-each select="root/vizsgak/vizsga">
                        <tr>
                            <td><xsl:value-of select="kurzus"/></td>
                            <td><xsl:value-of select="jegy"/></td>
                        </tr>
                    </xsl:for-each>
                    <tr>
                        <td>A felev atlaga</td>
                        <td><xsl:value-of select="sum(root/vizsgak/vizsga/jegy) div count(root/vizsgak/vizsga/jegy)"/></td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>