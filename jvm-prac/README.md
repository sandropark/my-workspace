# 멀티모듈 프로젝트

이 프로젝트는 다양한 기술 스택을 사용하는 여러 모듈로 구성된 멀티모듈 프로젝트입니다.

## 📁 프로젝트 구조

```
kotlin-prac/
├── kotlin-backend/         # Kotlin/Java Spring Boot 백엔드
│   ├── build.gradle
│   └── src/
│       ├── main/java/
│       └── test/java/
├── typescript-frontend/    # TypeScript 프론트엔드 (향후 추가)
├── python-service/         # Python 마이크로서비스 (향후 추가)
├── build.gradle           # 루트 빌드 설정
├── settings.gradle        # 멀티모듈 설정
└── README.md
```

## 🚀 모듈 소개

### kotlin-backend
- **기술 스택**: Kotlin, Java 21, Spring Boot 3.5.4
- **테스트 프레임워크**: Kotest
- **설명**: Spring Boot 기반 백엔드 서비스

### 향후 추가 예정 모듈
- **typescript-frontend**: React/Vue/Angular 등 TypeScript 기반 프론트엔드
- **python-service**: FastAPI/Django 등 Python 기반 마이크로서비스

## 🛠️ 빌드 및 실행

### 전체 프로젝트 빌드
```bash
./gradlew buildAll
```

### 전체 테스트 실행
```bash
./gradlew testAll
```

### 특정 모듈 빌드
```bash
./gradlew :kotlin-backend:build
```

### 특정 모듈 테스트
```bash
./gradlew :kotlin-backend:test
```

### kotlin-backend 실행
```bash
./gradlew :kotlin-backend:bootRun
```

## 📝 새 모듈 추가하기

1. 새 디렉토리 생성
2. `settings.gradle`에 모듈 추가:
   ```gradle
   include 'new-module-name'
   ```
3. 해당 모듈의 빌드 설정 파일 생성

## 🔧 개발 환경

- Java 21
- Gradle 8.x
- Kotlin 2.2.0 