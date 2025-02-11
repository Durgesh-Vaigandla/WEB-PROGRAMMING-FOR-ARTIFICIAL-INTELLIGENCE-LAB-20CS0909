<?php

use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('products');
});

Route::get('/cart', function () {
    return view('cart');
});
