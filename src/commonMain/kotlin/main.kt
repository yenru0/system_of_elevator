package yenru0.yrkaier.systemofelevator

import KR
import korlibs.event.*
import korlibs.time.*
import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.korge.tween.*
import korlibs.korge.view.*
import korlibs.image.color.*
import korlibs.image.font.*
import korlibs.image.format.*
import korlibs.image.text.*
import korlibs.io.file.std.*
import korlibs.korge.input.*
import korlibs.korge.view.Circle
import korlibs.logger.*
import korlibs.math.geom.*
import korlibs.math.interpolation.*

object FontLib {
    lateinit var MainFont: SystemFont


    suspend fun load() {
        MainFont = SystemFont("D2Coding")
    }

}


suspend fun main() = Korge(
    windowSize = Size(512, 512),
    backgroundColor = Colors.BLACK,
    virtualSize = Size(512, 512),
) {

    FontLib.load()
    val sceneContainer = sceneContainer()

    sceneContainer.changeTo { MyScene() }
}


class MyScene : Scene() {

    lateinit var backRect: Container
    lateinit var elevatorButtons: List<Circle>
    override suspend fun SContainer.sceneInit() {
        backRect = FixedSizeContainer(Size(512 - 40, 1000)).xy(20, 0)

        SolidRect(512 - 40, 1000, Colors.DARKVIOLET)
            .xy(0, 0).name("backplate").zIndex(-1).addTo(backRect)

        this += backRect
        val main_text = Text("엘리베이터 입니다.", font = FontLib.MainFont, color = Colors.WHITE).xy(5, 5)
        backRect += main_text

        val btn_x = Circle(fill = Colors.TRANSPARENT, strokeThickness = 3.0, stroke = Colors.WHITE).xy(5, 100)
        btn_x += Text(
            "1",
            font = FontLib.MainFont,
            color = Colors.WHITE,
            alignment = TextAlignment.MIDDLE_CENTER
        ).xy(btn_x.radius, btn_x.radius)

        btn_x.onClick { println((((it.view as Circle).lastChild as Text).text)) }

        backRect += btn_x


    }

    override suspend fun SContainer.sceneMain() {
        val minDegrees = (-32).degrees
        val maxDegrees = (+32).degrees


        addUpdater { dt: TimeSpan ->
            val scale = dt / (16).milliseconds
            if (input.keys[Key.W]) {
                if (backRect.y + 10 * scale > 0) {
                    backRect.y = 0.0
                } else {
                    backRect.y += 10 * scale
                }

            }
            if (input.keys[Key.S]) {
                if (backRect.y + backRect.height - 10 * scale < this.height) {
                    backRect.y = this.height - backRect.height
                } else {
                    backRect.y -= 10 * scale
                }


            }
        }


    }
}


// class ElevatorButton: Circle
