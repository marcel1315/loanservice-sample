## Project Setup

1. Build jar and docker images
    ```
    cd docker/
    ./0-build-jar-and-docker.sh
    ```
2. Launch database dockers and make new tables (and docker network). Wait 30s while db table boot up.
    ```
    ./1-setup-clean-db.sh
    ```
3. Launch nginx and application dockers. 3 api servers will launch. Wait 10s while applications boot up.
    ```
   ./2-setup-app.sh
    ```

## API

- domain and port: localhost:8091
- API docs: localhost:8091/swagger-ui/index.html

## DB

- mysql
  - USER_INFO, PRODUCT_INFO, PRODUCT_LIST 테이블로 구성 
  - 테이블 생성 스크립트: docker/mysql-init-script/create-table.sql
- redis
  - 상품 정보 호출 시 캐시로 사용

## Cleanup

- Cleanup docker containers, images and docker network created by this project
    ```
   ./3-cleanup.sh
    ```
