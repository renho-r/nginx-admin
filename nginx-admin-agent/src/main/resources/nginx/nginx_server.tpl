server {
    listen 80;
    server_name  localhost ;
    server_name_in_redirect off;
    include ${config_path_locations}/*.conf;
}