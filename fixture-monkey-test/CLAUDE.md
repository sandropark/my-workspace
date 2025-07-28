# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot 3.5.4 demonstration project showcasing Naver's Fixture Monkey library (v1.1.14) for test data generation. The project uses Java
21 and Gradle with the following key characteristics:

- **Primary Purpose**: Educational/example project demonstrating Fixture Monkey usage patterns
- **Framework**: Spring Boot with minimal dependencies (no web layer, databases, or external services)
- **Test Focus**: Comprehensive examples of test fixture generation for domain objects

## Build Commands

```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests FixtureMonkeyExampleTest

# Build project
./gradlew build

# Run Spring Boot application (minimal - just starts and stops)
./gradlew bootRun
```

## Project Architecture

### Domain Models

The project defines three main domain entities in `src/main/java/com/sandro/baseproject/`:

- **User** (`User.java:13-22`): Core user entity with personal info, activity status, creation timestamp, hobbies list, and embedded Address
- **Address** (`Address.java:10-15`): Value object representing physical addresses
- **Product** (`Product.java:13-22`): E-commerce product entity with pricing, inventory, and metadata

All domain objects use Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) for boilerplate reduction.

### Test Structure

`FixtureMonkeyExampleTest.java` demonstrates two Fixture Monkey configuration approaches:

1. **Bean Introspection** (`fixtureMonkey`, line 19-21): Uses getter/setter methods
2. **Field Reflection** (`fieldFixtureMonkey`, line 24-26): Direct field access (recommended for Lombok)

### Key Fixture Monkey Patterns

The test class showcases these essential patterns:

- Basic random object generation (`giveMeOne()`, `giveMe()`)
- Specific property setting (`set()` method chaining)
- Nested object property setting (dot notation: `"address.city"`)
- Collection property assignment
- Null value assignment (`setNull()`)
- Conditional value constraints

## Development Notes

- **Java Version**: Project requires Java 21 (configured in build.gradle:12)
- **Lombok Integration**: All domain objects use Lombok - prefer Field Reflection introspector for Fixture Monkey
- **Test Data Strategy**: Use Fixture Monkey for all test object creation instead of manual constructors
- **Package Structure**: Single package `com.sandro.baseproject` contains all classes