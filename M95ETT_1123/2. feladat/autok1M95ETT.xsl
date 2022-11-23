<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">

        <html>
            <body>
                <h2>Autok rendszama</h2>
                <table border = "4">
                    <tr bgcolor="#9acd32">
                        <th>Tipus</th>
                        <th>Rendszam</th>
                        <th>Ar</th>
                    </tr>

                    <xsl:for-each select="autok/auto">
                        <xsl:sort select="ar"/>
                        <tr>
                            <td><xsl:value-of select="tipus"/></td>
                            <td><xsl:value-of select="@rsz"/></td>
                            <td><xsl:value-of select="ar"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>