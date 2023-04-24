# How to start Docker container with HTML file from /Files/HTML (without COPY in dockerfile)
1. `cd Docker - RomNero`
2. `docker build -t nginx:v1 .`
3. `docker run -d --rm --name nginx -p8080:80 -v "<Full Path>Tutorials\Docker\Docker - RomNero\Files\HTML:/var/www/html" nginx:v1` 
4. Open `localhost:8080`
