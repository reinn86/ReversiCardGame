package application.view.scene.matchmake

import application.view.Panel
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Image
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JLabel

class MatchMake : Panel(){
    //components
    private val matchingTextLabel = JLabel()
    private val bgPath = "src/main/resources/image/bg_title.jpeg"

    @Override
    override fun paintComponent(g: Graphics) {
        //locations
        val bgLocationX = 0
        val bgLocationY = 0

        //sizes
        val bgImageWidth = vw(100.0)
        val bgImageHeight = vh(100.0)

        //images
        val bgImage = ImageIO.read(File(bgPath)).getScaledInstance(
            bgImageWidth,
            bgImageHeight,
            Image.SCALE_DEFAULT
        )
        g.drawImage(bgImage,bgLocationX,bgLocationY,this)
    }

    init {
        //mattingTextLabel
        matchingTextLabel.size = Dimension(vw(80.0),vh(10.0))
        matchingTextLabel.text = "マッチング中..."
        matchingTextLabel.setLocation(vw(10.0),vh(70.0))

        add(matchingTextLabel)
    }
}