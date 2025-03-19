
## Tomcat 8.5

### tomcat@8 설치

```shell
brew install tomcat@8
```

### tomcat@8 실행

```shell
brew services start tomcat@8
```

### tomcat@8 중지

```shell
brew services stop tomcat@8
```

### tomcat@8 재실행

```shell
brew services restart tomcat@8
```

## 설정 파일 위치

```
/opt/homebrew/etc/tomcat@8
```

### 주요 설정 파일

- /opt/homebrew/Cellar/tomcat@8/8.5.100/libexec/conf/server.xml
- /opt/homebrew/Cellar/tomcat@8/8.5.100/libexec/conf/web.xml
- /opt/homebrew/Cellar/tomcat@8/8.5.100/libexec/conf/context.xml
- /opt/homebrew/Cellar/tomcat@8/8.5.100/libexec/conf/tomcat-users.xml

## 웹 애플리케이션(webapp) 디렉토리 위치

```
/opt/homebrew/Cellar/tomcat@8/8.5.100/libexec/webapps
```

Tomcat 8의 실행 스크립트는 `/usr/local/opt/tomcat@8/bin/` 디렉토리에 있습니다.

Homebrew 설치 경로는 시스템에 따라 약간 다를 수 있으니, 정확한 위치를 확인하려면 다음 명령어를 사용해 보세요:
```bash
brew info tomcat@8
```

이 명령어는 설치 경로와 함께 유용한 정보를 제공합니다.


확인해 주셔서 감사합니다. 이제 Tomcat 8의 정확한 위치를 확인했습니다:

웹 애플리케이션(webapp) 디렉토리 위치:
```
/opt/homebrew/Cellar/tomcat@8/8.5.100/libexec/webapps
```

설정 파일 위치:
```
/opt/homebrew/etc/tomcat@8/
```

Homebrew로 설치한 Tomcat 8은 다음과 같은 구조로 되어 있습니다:
1. 설정 파일은 `/opt/homebrew/etc/tomcat@8/`에 위치
2. 웹 애플리케이션은 `/opt/homebrew/Cellar/tomcat@8/8.5.100/libexec/webapps/`에 위치
3. Context 설정은 `/opt/homebrew/etc/tomcat@8/Catalina/localhost/`에 위치

웹 애플리케이션을 배포하려면 위에서 찾은 webapps 디렉토리에 WAR 파일을 복사하거나 해당 디렉토리에 애플리케이션 폴더를 생성하면 됩니다.