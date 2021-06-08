composer install
npm install
php artisan key:generate
chmod 755 -R *
chmod -R o+w storage
php artisan cache:clear
php artisan view:clear
php artisan config:clear
