# ng-pdf

Rendering PDFs out of OpenOffice/LibreOffice document templates (*.odt).

## Source

```
wget https://bitbucket.org/xcgd/py3o.fusion/raw/76dcb04477c4ddcaa303db94ae340561c2da75a9/docker-compose.yml
```


## Usage

```
docker-compose up
open http://py3o.docker-machine.epages.works:8765/
```

## Render PDF

```
http --verbose --debug --form --output fusion2pdf.pdf POST http://py3o.docker-machine.epages.works:8765/form \
    image1@test.png \
    tmpl_file@fusion2pdf.odt \
    targetformat=pdf \
    datadict='{"document":{"person_surname":"Fischer", "person_name":"Jens", "person_company":"ePages GmbH", "person_url":"http://www.epages.com"}}' \
    image_mapping='{"image1":"logo"}'
```

```
HTTPie 1.0.0-dev
Requests 2.10.0
Pygments 2.1.3
Python 2.7.12 (default, Jun 29 2016, 14:05:02)
[GCC 4.2.1 Compatible Apple LLVM 7.3.0 (clang-703.0.31)]
/usr/local/opt/python/bin/python2.7
Darwin 15.6.0

<Environment {
    "colors": 256,
    "config": {
        "__meta__": {
            "about": "u'HTTPie configuration file'",
            "help": "u'https://github.com/jkbrzt/httpie#config'",
            "httpie": "u'1.0.0-dev'"
        },
        "default_options": "[]"
    },
    "config_dir": "/Users/jfischer/.httpie",
    "is_windows": false,
    "stderr": "<open file '<stderr>', mode 'w' at 0x10faab1e0>",
    "stderr_isatty": true,
    "stdin": "<open file '<stdin>', mode 'r' at 0x10faab0c0>",
    "stdin_encoding": "UTF-8",
    "stdin_isatty": true,
    "stdout": "<open file '<stdout>', mode 'w' at 0x10faab150>",
    "stdout_encoding": "UTF-8",
    "stdout_isatty": true
}>

>>> requests.request(**{
    "allow_redirects": false,
    "auth": "None",
    "cert": "None",
    "data": {
        "datadict": "u'{\"document\":{\"person_surname\":\"Fischer\", \"person_name\":\"Jens\", \"person_company\":\"ePages GmbH\", \"person_url\":\"http://www.epages.com\"}}'",
        "image_mapping": "u'{\"image1\":\"logo\"}'",
        "targetformat": "u'pdf'"
    },
    "files": {
        "image1": "(u'test.png', <_io.BytesIO object at 0x1104def50>, 'image/png')",
        "tmpl_file": "(u'fusion2pdf.odt', <_io.BytesIO object at 0x11062e710>, 'application/vnd.oasis.opendocument.text')"
    },
    "headers": {
        "User-Agent": "HTTPie/1.0.0-dev"
    },
    "method": "u'post'",
    "params": {},
    "proxies": {},
    "stream": true,
    "timeout": 30,
    "url": "u'http://py3o.docker-machine.epages.works:8765/form'",
    "verify": true
})
```



## Links

* [Docker Hub](https://hub.docker.com/r/xcgd/py3o.fusion/)
* [BitBucket](https://bitbucket.org/faide/py3o.fusion)
