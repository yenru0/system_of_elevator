import korlibs.korge.gradle.*

plugins {
	alias(libs.plugins.korge)
}

korge {
	id = "yenru0.yrkaier.systemofelevator"
    name = "system_of_elevator"

// To enable all targets at once

	//targetAll()

// To enable targets based on properties/environment variables
	//targetDefault()

// To selectively enable targets
	
	targetJvm()
	targetJs()
    //targetWasm()
	//targetDesktop()
	//targetIos()
	//targetAndroid()

	serializationJson()

    jvmMainClassName = "yenru0.yrkaier.systemofelevator.MainKt"
}


dependencies {
    add("commonMainApi", project(":deps"))
    //add("commonMainApi", project(":korge-dragonbones"))
}

