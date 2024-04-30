import * as Stats from 'stats.js';

export class Statistics {
    static statistics: Stats = new Stats();

    static show() {
        Statistics.statistics.dom.id = "statisticsFPS";
        Statistics.statistics.dom.style.top = "auto"
        Statistics.statistics.dom.style.left = "auto"
        Statistics.statistics.dom.style.bottom = "0px"
        Statistics.statistics.dom.style.right = "0px"
        Statistics.statistics.dom.style.display = 'block'
        document.body.appendChild(Statistics.statistics.dom);
    }

    static update() {
        Statistics.statistics.update()
    }

    static stop() {
        Statistics.statistics.end()
        document.getElementById('statisticsFPS')!.style.display = 'none'
    }
}
