import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import * as olProj from 'ol/proj';
import Feature from 'ol/Feature';
import Point from 'ol/geom/Point';
import { Icon, Style } from 'ol/style';
import VectorSource from 'ol/source/Vector';
import VectorLayer from 'ol/layer/Vector';

@Component({
  selector: 'app-vahicles-coordinates',
  templateUrl: './vahicles-coordinates.component.html',
  styleUrls: ['./vahicles-coordinates.component.css']
})
export class VahiclesCoordinatesComponent implements OnInit, OnDestroy {
  eventSource?: EventSource;
  map?: Map;
  vectorLayer?: VectorLayer<VectorSource<Point>>;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.map = new Map({
      target: 'map',
      layers: [
        new TileLayer({
          source: new OSM()
        })
      ],
      view: new View({
        center: olProj.fromLonLat([0, 0]),
        zoom: 2
      })
    });

    this.vectorLayer = new VectorLayer({
      source: new VectorSource()
    });

    if (this.map && this.vectorLayer) {
      this.map.addLayer(this.vectorLayer);

      this.eventSource = new EventSource('http://localhost:5000/events');
      this.eventSource.onmessage = (event) => {
        console.log(event.data);

        const data = event.data;
        const tokens = data.split(',')
        const latitudeStr = tokens[0];
        const longitudeStr = tokens[1];
        console.log(latitudeStr, longitudeStr);
        if (latitudeStr && longitudeStr) {
          const latitude = parseFloat(latitudeStr);
          const longitude = parseFloat(longitudeStr);
          
          const point = new Point(olProj.fromLonLat([longitude, latitude]));
          const feature = new Feature({
            geometry: point
          });
          feature.setStyle(new Style({
            image: new Icon({
              src: 'https://cdn-icons-png.flaticon.com/512/5225/5225195.png',
              scale: 0.1
            })
          }));
          
          if (this.vectorLayer?.getSource()) {
            this.vectorLayer?.getSource()?.addFeature(feature);
          }
        }
      };
    }
  }

  ngOnDestroy(): void {
    this.eventSource?.close();
  }
}
