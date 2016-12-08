angular.module('dm.config', [])
    .config(function (ngDialogProvider) {
        ngDialogProvider.setDefaults({
            showClose: true,
            closeByDocument:false
        });
    })
    .config(['$provide', function($provide) {
        $provide.decorator('$rootScope', ['$delegate', function($delegate) {
            Object.defineProperty($delegate.constructor.prototype, '$onRootScope', {
                value: function(name, listener) {
                    var unsubscribe = $delegate.$on(name, listener);
                    this.$on('$destroy', unsubscribe);
                    return unsubscribe;
                },
                enumerable: false
            });
            return $delegate;
        }]);
    }])
    .config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
        cfpLoadingBarProvider.includeSpinner = false; // 进度关闭旋转效果
    }])
    .constant('uibPaginationConfig', {
        itemsPerPage: 10,
        boundaryLinks: true,
        directionLinks: true,
        firstText: '«',
        previousText: '‹',
        nextText: '›',
        lastText: '»',
        maxSize: 10,
        rotate: false
    })
    .constant('uibPagerConfig', {
        itemsPerPage: 10,
        previousText: '‹',
        nextText: '›',
        align: true
    })
    .config(['$uibTooltipProvider', function ($uibTooltipProvider) {
        $uibTooltipProvider.options({
            appendToBody: true
        });
    }])
    .config(['growlProvider', function(growlProvider) {
        growlProvider.globalTimeToLive({
            success: 2000,
            error: -1,
            warning: 3000,
            info: 4000
        });
        growlProvider.globalDisableCountDown(true);
    }])
    .config(function($httpProvider) {
        // register the interceptor via an anonymous factory
        $httpProvider.interceptors.push(function($q, growl, $rootScope, ipCookie) {
            return {
                'request': function (config) { // 注入post 的 header
                    if (config.method.toLowerCase() === 'post') {
                        config.headers.Accept = config.headers.Accept || '*/*';
                        if(config.headers['Content-Type'] === 'application/x-www-form-urlencoded') {
                            if (typeof config.data === 'object') { // 转换js对象
                                config.data = $.param(config.data);
                            }
                        }
                        var headerName = $rootScope.headerName;
                        config.headers[headerName] = $rootScope.token;
                    }

                    return config;
                },
                'response': function(response) {
                    var url = response.config.url;
                    if (url.length > 7 && url.startsWith('/api')) {
                        if (response.data.errCode) {
                            growl.error(response.data.errMsg || response.data.errName);
                            console.debug('url: ' + url, ' msg: ' , response.data.errMsg || response.data.errName);
                            response.data = response.data.data;
                            return $q.reject(response);
                        } else if (response.data.errCode === 0) {
                            response.data = response.data.data;
                            return $q.resolve(response);
                        }
                    }
                    return response;
                },
                'responseError': function(rejection) {
                    if (rejection.data && rejection.data.Message) {
                        growl.error(rejection.data.Message);
                    }
                    return $q.reject(rejection);
                }
            };
        });
    })
    .config(['$controllerProvider', '$compileProvider', function($controllerProvider, $compileProvider) {
        // this option might be handy for migrating old apps, but please don't use
        // it in new ones!
        if ($controllerProvider.allowGlobals) {
            $controllerProvider.allowGlobals();
        }
        if ($compileProvider.debugInfoEnabled) {
            $compileProvider.debugInfoEnabled(true);
        }
    }]);
