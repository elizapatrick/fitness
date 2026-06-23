@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.

@echo off
setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@REM Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome
set JAVA_EXE=java.exe
%JAVA_EXE% -version >nul 2>&1
if %ERRORLEVEL% equ 0 goto execute
echo. 1>&2
echo Error: JAVA_HOME is not set and no 'java' command could be found in your PATH. 1>&2
echo. 1>&2
goto error

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute
echo. 1>&2
echo Error: JAVA_HOME is set to an invalid directory: %JAVA_HOME% 1>&2
echo Please set JAVA_HOME to point to a valid Java home folder. 1>&2
echo. 1>&2
goto error

:execute
@REM Initializing the defaults
setlocal enabledelayedexpansion
for /F "usebackq delims=" %%a in ("%APP_HOME%\.mvn\wrapper\maven-wrapper.properties") do (
    if "%%a" not equ "" (
        set "property=%%a"
        setlocal enabledelayedexpansion
        %%property%%
    )
)
set MAVEN_PROJECTBASEDIR=%APP_HOME%
set WRAPPER_JAR=%APP_HOME%\.mvn\wrapper\maven-wrapper.jar
if exist "%WRAPPER_JAR%" (
    "%JAVA_EXE%" ^
      -cp "%WRAPPER_JAR%" ^
      "-Dmaven.multiModuleProjectDirectory=%APP_HOME%" ^
      org.apache.maven.wrapper.MavenWrapperMain %*
) else (
    echo Error: maven-wrapper.jar not found in %APP_HOME%\.mvn\wrapper ^1>&2
    exit /b 1
)
endlocal
endlocal
exit /b %ERRORLEVEL%

:error
exit /b 1
