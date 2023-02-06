// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
	repositories {
		google()
		mavenCentral()
	}
	dependencies {
		classpath("com.android.tools.build:gradle:7.3.1")
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
		classpath("org.jetbrains.kotlin:kotlin-android-extensions:1.6.0")
		classpath("com.google.gms:google-services:4.3.14")
		// NOTE: Do not place your application dependencies here; they belong
		// in the individual module build.gradle files
	}
}
allprojects {
	repositories {
		google()
		mavenCentral()
	}
}
// create the clean task
task("clean") {
	delete(rootProject.buildDir)
}