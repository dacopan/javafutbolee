var gulp = require('gulp');
var plugins = require('gulp-load-plugins')({lazy: false});
var rimraf = require('rimraf');



var paths = {
    dist: {
        css: './resources/primefaces-dtic_theme/',
        js: './resources/dt1c/js',
        fonts: './resources/dt1c/fonts'
    },
    less: ['./assets/less/dtictheme.less'],
    js: ['./assets/js/*.js', './assets/js/**/*.js'],
    vendor: {
        css: [
            './bower_components/animate.css/animate.css',
            './bower_components/font-awesome/css/font-awesome.css'
        ],
        js: [
            './bower_components/smooth-scroll/dist/js/smooth-scroll.js',
            './bower_components/scrollmagic/scrollmagic/ScrollMagic.min.js',
            './bower_components/wow/dist/wow.js',
            './bower_components/smoothscroll/SmoothScroll.js',
            './bower_components/bootstrap/js/collapse.js',
            './bower_components/bootstrap/js/transition.js',
            './bower_components/smooth-scroll/dist/js/smooth-scroll.js',
            './bower_components/jQuery-One-Page-Nav/jquery.nav.js',
            './bower_components/parallax/deploy/jquery.parallax.min.js'
        ],
        fonts: [
            './bower_components/font-awesome/fonts/fontawesome-webfont.*'
        ]

    },
    preloader: {
        css: ['./assets/less/preloader.less']
    }
};

/**
 * removes css- and js-dist folder.
 */
gulp.task('clean', function () {
    rimraf.sync(paths.dist.css + 'theme.css');
    rimraf.sync(paths.dist.js + 'dt1ctheme.js');
});

/**
 * compiles less files into css.
 */
gulp.task('less', function () {
    gulp.src(paths.less)
            .pipe(plugins.concat('theme.css'))
            .pipe(plugins.less())
            .pipe(plugins.minifyCss())
            .pipe(gulp.dest(paths.dist.css));
});


gulp.task('js', function () {
    gulp.src(paths.js)
            .pipe(plugins.concat('dt1ctheme.js'))
            .pipe(plugins.uglify({mangle: false}))
            .pipe(gulp.dest(paths.dist.js));
});

gulp.task('fonts', function () {
    gulp.src(paths.vendor.fonts)
            .pipe(gulp.dest(paths.dist.fonts));

});


/**
 * copies vendor specific files to the public folder.
 */
gulp.task('vendor', function () {
    gulp.src(paths.vendor.css)
            .pipe(plugins.concat('vendor.css'))
            .pipe(plugins.minifyCss())
            .pipe(gulp.dest(paths.dist.js + '/../css'));

    gulp.src(paths.preloader.css)
            .pipe(plugins.concat('preloader.css'))
            .pipe(plugins.less())
            .pipe(plugins.minifyCss())
            .pipe(gulp.dest(paths.dist.js + '/../css'));


    gulp.src(paths.vendor.js)
            .pipe(plugins.concat('vendor.js'))
            .pipe(plugins.uglify({mangle: false}))
            .pipe(gulp.dest(paths.dist.js));

});

/**
 * optimizes the output in terms of minification and concatenation.
 */
gulp.task('dist', function () {
    // add some optimizations (?)
});



gulp.task('build', ['clean', 'less', 'js', 'dist']);
gulp.task('nvendor', ['vendor']);
gulp.task('allx', ['build', 'nvendor', 'fonts']);

gulp.task('default', ['build']);