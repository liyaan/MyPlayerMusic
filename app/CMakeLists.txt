cmake_minimum_required(VERSION 3.10.2)

project("myplayermusic")


file(GLOB SOURCE src/main/cpp/*.cpp)
add_library(
        native-lib
        SHARED
        ${SOURCE})

find_library(
        log-lib
        log )
include_directories(src/main/cpp/include)

set(my_lib_path  ${CMAKE_SOURCE_DIR}/libs/${ANDROID_ABI})
set(CMAKE_CXX_FLAGS  "${CMAKE_CXX_FLAGS} -L${my_lib_path}")
message("${CMAKE_CXX_FLAGS} -L${my_lib_path}")
target_link_libraries(
        native-lib
        avfilter avformat avcodec avutil swresample swscale
        ${log-lib}
        z
        OpenSLES
        android
)