import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
	id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
	id("org.liquibase.gradle") version "2.2.0"

}

group = "phoal"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
	mavenLocal()
}

configurations {
	liquibaseRuntime.get().extendsFrom(configurations.runtimeOnly.get())
}

val liquibaseVersion = "4.20.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka-reactive:4.1.1")

	runtimeOnly("org.postgresql:r2dbc-postgresql:1.0.5.RELEASE")
	runtimeOnly("org.postgresql:postgresql:42.6.0")

	liquibaseRuntime("org.liquibase:liquibase-core:$liquibaseVersion")
	liquibaseRuntime("info.picocli:picocli:4.7.3")
	liquibaseRuntime("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")

	testImplementation("io.projectreactor:reactor-test")
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
	android.set(false)
	outputToConsole.set(true)
	outputColorName.set("RED")
}

	/**
	 * TODO Automate processes
	 * liquibase
		 ./gradlew liquibase-schema-name=message liquibase-changelog-file=db-changelog/message-master.xml liquibase-db-username=phoal --liquibase-db-password=Snl6wzCJWsWL4BD3-xuB5w update --no-daemon
		 LIQUIBASE_SCHEMA_NAME=message;LIQUIBASE_CHANGELOG_FILE=db-changelog/message-master.xml;LIQUIBASE_DB_USERNAME=phoal;LIQUIBASE_DB_PASSWORD=Snl6wzCJWsWL4BD3-xuB5w

	 * kafka docker
	 	cd ~/Kafka/kafka_2.13-3.7.0/bin
		./zookeeper-server-start.sh ../config/zookeeper.properties
		./kafka-server-start.sh ../config/server.properties

	 * init
		./kafka-topics.sh --create --topic kafka-message --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1

	 */

liquibase {
	activities.register("main") {
		this.arguments = mapOf(
			"liquibaseSchemaName" to System.getenv("LIQUIBASE_SCHEMA_NAME"),
			"logLevel" to "info",
			"changeLogFile" to System.getenv("LIQUIBASE_CHANGELOG_FILE"),
			"url" to (System.getenv("LIQUIBASE_DB_URL") ?: "jdbc:postgresql://hearty-penguin-6660.6xw.aws-ap-southeast-1.cockroachlabs.cloud:26257/postgres?sslmode=verify-full"),
			"username" to (System.getenv("LIQUIBASE_DB_USERNAME") ?: "postgres"),
			"password" to (System.getenv("LIQUIBASE_DB_PASSWORD") ?: "password")
		)
	}
	runList = "main"
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
