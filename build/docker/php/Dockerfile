FROM php:8.3-fpm-alpine AS prod

WORKDIR /var/www/html

RUN apk add --no-cache libzip-dev libpng-dev icu-dev libjpeg-turbo-dev shadow  && \
    docker-php-ext-install pdo pdo_mysql && \
    docker-php-ext-enable pdo_mysql && \
    docker-php-ext-configure zip && \
    docker-php-ext-install zip && \
    docker-php-ext-configure gd --with-jpeg && \
    docker-php-ext-install gd && \
    docker-php-ext-configure intl && \
    docker-php-ext-install intl && \
    docker-php-ext-configure pcntl --enable-pcntl && \
    docker-php-ext-install pcntl && \
    groupmod --gid 1000 www-data && \
    usermod --uid 1000 --gid 1000 www-data

VOLUME ["/var/www/html"]

COPY --chown=1000 src /var/www/html
RUN mkdir /var/www/html/storage/fonts && chown 1000:1000 -R /var/www/html/storage/fonts


FROM prod AS dev

# install utilities
RUN apk add bash nano rsync zip unzip nodejs npm

# install composer
COPY --from=composer:2 /usr/bin/composer /usr/local/bin/composer

# used by vite dev
ADD --chown=1000 build/certs/certificate.chained.crt /etc/ssl/certificate.chained.crt
ADD --chown=1000 build/certs/wildcard.private.key /etc/ssl/wildcard.private.key