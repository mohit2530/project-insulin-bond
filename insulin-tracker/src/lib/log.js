import debug from 'debug';

let BASE = 'project-insulin',
    colors = {
        trace: 'lightblue',
        info:'white',
        warn : 'orange',
        error: 'red'
    };

// enable logging of trace, info, warn and error
class Log {
    consoleMessage(level, message, source) {
        const nameSpace = `${BASE}:${level}`,
            createDebug = debug(nameSpace);

        createDebug.color = colors[level];

        if (source) {
            createDebug(source, message);
        }
        else {
            createDebug(message);
        }
    };

    trace(message, source) {
        return this.consoleMessage('trace', message, source);
    };

    info(message, source) {
        return this.consoleMessage('info', message, source);
    }

    warn(message, source) {
        return this.consoleMessage('warn', message, source);
    }

    error(message, source) {
        return this.consoleMessage('erorr', message, source);
    }
}

export default new Log();