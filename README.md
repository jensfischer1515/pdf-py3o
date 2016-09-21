# pdf-py3o

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

These examples use [httpie](https://github.com/jkbrzt/httpie) command line client.

Simple PDF using custom data:

```
http --form --output fusion2pdf.pdf \
     POST http://py3o.docker-machine.epages.works:8765/form \
     targetformat=pdf \
     tmpl_file@fusion2pdf.odt \
     image_mapping='{}' \
     datadict=@fusion2pdf.json
```

Invoice PDF with custom logo and a variable number of line items:

```
http --form --output invoice.pdf \
     POST http://py3o.docker-machine.epages.works:8765/form \
     targetformat=pdf \
     tmpl_file@invoice.odt \
     image_mapping=@image_mapping.json \
     logo@test.png \
     datadict=@invoice.json
```


## Links

* [Docker Hub](https://hub.docker.com/r/xcgd/py3o.fusion/)
* [BitBucket](https://bitbucket.org/faide/py3o.fusion)
* [Templating documentation](http://py3otemplate.readthedocs.io/en/latest/templating.html)
