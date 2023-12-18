import org.gradle.api.JavaVersion.VERSION_17

plugins {
	id("com.android.application")
	kotlin("android")
	id("com.google.gms.google-services")
}
android {
	compileSdk = 34
	defaultConfig {
		applicationId = "com.example.buildmypc"
		minSdk = 29
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	buildTypes {
		getByName("release") {
			isMinifyEnabled = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility(VERSION_17)
		targetCompatibility(VERSION_17)
	}
	buildFeatures { viewBinding = true }
	dependenciesInfo {
		includeInApk = true
		includeInBundle = true
	}
	namespace = "com.example.buildmypc"
}
dependencies {
	implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
	implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.10")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.10.0")
	implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
	implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
	implementation("androidx.navigation:navigation-ui-ktx:2.7.3")
	implementation("androidx.legacy:legacy-support-v4:1.0.0")
	implementation("androidx.recyclerview:recyclerview:1.3.1")
	implementation("androidx.browser:browser:1.6.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")
	implementation("androidx.annotation:annotation:1.7.1")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
