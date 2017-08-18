/**
 * Created by wen on 2017/8/9.
 */
function accAdd(arg1, arg2) {
    var r1, r2, m;
    try {
        r1 = arg1.toString().split('.')[1].length;
    } catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split('.')[1].length;
    } catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));
    arg1 = isNaN(parseFloat(arg1)) ? 0 : parseFloat(arg1);
    arg2 = isNaN(parseFloat(arg2)) ? 0 : parseFloat(arg2);
    return (arg1 * m + arg2 * m) / m;
}