/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ *//* TextFrame class */
package net.mooctest;
import java.util.StringTokenizer;
import java.awt.*;
import java.io.*;
import java.net.*;

public class TextFrame extends Panel {public static class __CLR4_4_111w11wlpnjv53u{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0035\u0037\u0033\u005c\u005c\u0031\u0031\u0037\u0038\u0033\u0030\u005c\u005c\u0050\u005f\u0051\u0075\u0065\u0075\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701491091874L,8589935092L,1494,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
    String file_name;

    String[] lines;
    int n_lines, highlight;
    int width = 300;
    int height = 400;
    int delay = 400;
    int max_lines = 1000;
    Dimension offscreensize = null;
    Image offscreen = null;
    Graphics offGraphics = null;

    static final int dx = 2;
    Font font;
    int line_space = 14;
    int font_size = 10;
    int start;

    static final String ignore_trigger = "/*-";
    static final String start_sign = "/*-------";
    static final String end_sign = "//-";

    private StringBuffer trim(String s) {try{__CLR4_4_111w11wlpnjv53u.R.inc(1364);
        __CLR4_4_111w11wlpnjv53u.R.inc(1365);int pos;
        __CLR4_4_111w11wlpnjv53u.R.inc(1366);StringBuffer sb;

        __CLR4_4_111w11wlpnjv53u.R.inc(1367);pos = s.indexOf(ignore_trigger);
        __CLR4_4_111w11wlpnjv53u.R.inc(1368);if ((((pos >= 0)&&(__CLR4_4_111w11wlpnjv53u.R.iget(1369)!=0|true))||(__CLR4_4_111w11wlpnjv53u.R.iget(1370)==0&false))) {{
            __CLR4_4_111w11wlpnjv53u.R.inc(1371);sb = new StringBuffer(s.substring(0,pos)); //sb.setLength(pos);
        } }else 
            {__CLR4_4_111w11wlpnjv53u.R.inc(1372);sb = new StringBuffer( s );
        }__CLR4_4_111w11wlpnjv53u.R.inc(1373);return sb;
    }finally{__CLR4_4_111w11wlpnjv53u.R.flushNeeded();}}
                
    private String expandtabs( StringBuffer sb ) {try{__CLR4_4_111w11wlpnjv53u.R.inc(1374);
        __CLR4_4_111w11wlpnjv53u.R.inc(1375);int i, len;
        __CLR4_4_111w11wlpnjv53u.R.inc(1376);len = sb.length();
        __CLR4_4_111w11wlpnjv53u.R.inc(1377);i = 0;
        __CLR4_4_111w11wlpnjv53u.R.inc(1378);while( (((i < len )&&(__CLR4_4_111w11wlpnjv53u.R.iget(1379)!=0|true))||(__CLR4_4_111w11wlpnjv53u.R.iget(1380)==0&false))) {{
            __CLR4_4_111w11wlpnjv53u.R.inc(1381);if ( (((sb.charAt( i ) == '\t' )&&(__CLR4_4_111w11wlpnjv53u.R.iget(1382)!=0|true))||(__CLR4_4_111w11wlpnjv53u.R.iget(1383)==0&false))) {{
	      __CLR4_4_111w11wlpnjv53u.R.inc(1384);for (int k = 0; (((k < 8)&&(__CLR4_4_111w11wlpnjv53u.R.iget(1385)!=0|true))||(__CLR4_4_111w11wlpnjv53u.R.iget(1386)==0&false)); k++) {{
                __CLR4_4_111w11wlpnjv53u.R.inc(1387);sb.setCharAt(i,' ');
                __CLR4_4_111w11wlpnjv53u.R.inc(1388);sb.insert(i,' ');
                __CLR4_4_111w11wlpnjv53u.R.inc(1389);len++;
	      }
            }}
            }__CLR4_4_111w11wlpnjv53u.R.inc(1390);i++;
        }
        }__CLR4_4_111w11wlpnjv53u.R.inc(1391);return sb.toString();        
    }finally{__CLR4_4_111w11wlpnjv53u.R.flushNeeded();}}

    private void SetFont( Graphics g ) {try{__CLR4_4_111w11wlpnjv53u.R.inc(1392);
        __CLR4_4_111w11wlpnjv53u.R.inc(1393);FontMetrics fm;
        __CLR4_4_111w11wlpnjv53u.R.inc(1394);this.setBackground( Color.white );
        __CLR4_4_111w11wlpnjv53u.R.inc(1395);font = new Font( "Dialog", Font.PLAIN, font_size );
        __CLR4_4_111w11wlpnjv53u.R.inc(1396);fm = g.getFontMetrics( font );
        __CLR4_4_111w11wlpnjv53u.R.inc(1397);line_space = fm.getHeight() + 1;
        __CLR4_4_111w11wlpnjv53u.R.inc(1398);g.setFont( font );
    }finally{__CLR4_4_111w11wlpnjv53u.R.flushNeeded();}}

    private int ReadSource( DataInputStream ds ) {try{__CLR4_4_111w11wlpnjv53u.R.inc(1399);
        __CLR4_4_111w11wlpnjv53u.R.inc(1400);int cnt = 0;
        __CLR4_4_111w11wlpnjv53u.R.inc(1401);StringBuffer sb;
                
        __CLR4_4_111w11wlpnjv53u.R.inc(1402);try {
            __CLR4_4_111w11wlpnjv53u.R.inc(1403);while( true ) {{
                __CLR4_4_111w11wlpnjv53u.R.inc(1404);String s;
                __CLR4_4_111w11wlpnjv53u.R.inc(1405);if ( (s = ds.readLine()) != null ) {{
                    __CLR4_4_111w11wlpnjv53u.R.inc(1408);if ( (((s.startsWith( start_sign ) )&&(__CLR4_4_111w11wlpnjv53u.R.iget(1409)!=0|true))||(__CLR4_4_111w11wlpnjv53u.R.iget(1410)==0&false))) {{
                        __CLR4_4_111w11wlpnjv53u.R.inc(1411);n_lines = 0;
                        __CLR4_4_111w11wlpnjv53u.R.inc(1412);cnt = 0;
                    } }else {__CLR4_4_111w11wlpnjv53u.R.inc(1413);if ((((s.trim().startsWith(ignore_trigger))&&(__CLR4_4_111w11wlpnjv53u.R.iget(1414)!=0|true))||(__CLR4_4_111w11wlpnjv53u.R.iget(1415)==0&false))) {{
                        __CLR4_4_111w11wlpnjv53u.R.inc(1416);continue;
                    } }else {__CLR4_4_111w11wlpnjv53u.R.inc(1417);if ((((s.startsWith(end_sign))&&(__CLR4_4_111w11wlpnjv53u.R.iget(1418)!=0|true))||(__CLR4_4_111w11wlpnjv53u.R.iget(1419)==0&false))) {{
                        __CLR4_4_111w11wlpnjv53u.R.inc(1420);break;
                    } }else {{
                        __CLR4_4_111w11wlpnjv53u.R.inc(1421);sb = trim( s );
                        /* Copy the buffer into a string */
                        __CLR4_4_111w11wlpnjv53u.R.inc(1422);lines[cnt++] = expandtabs( sb );
                    } 
                }}}} }else
                    {__CLR4_4_111w11wlpnjv53u.R.inc(1423);break;
            }}
        }} catch( IOException e ) {}
        __CLR4_4_111w11wlpnjv53u.R.inc(1424);return cnt;
    }finally{__CLR4_4_111w11wlpnjv53u.R.flushNeeded();}}

    public TextFrame( String fn ) {try{__CLR4_4_111w11wlpnjv53u.R.inc(1425);
        __CLR4_4_111w11wlpnjv53u.R.inc(1426);FileInputStream instream;

        __CLR4_4_111w11wlpnjv53u.R.inc(1427);file_name = fn;

        __CLR4_4_111w11wlpnjv53u.R.inc(1428);File source = new File( file_name );
        
        __CLR4_4_111w11wlpnjv53u.R.inc(1429);if ( (((source.exists() && source.isFile() )&&(__CLR4_4_111w11wlpnjv53u.R.iget(1430)!=0|true))||(__CLR4_4_111w11wlpnjv53u.R.iget(1431)==0&false))) {{
            __CLR4_4_111w11wlpnjv53u.R.inc(1432);try {
                __CLR4_4_111w11wlpnjv53u.R.inc(1433);lines = new String[ max_lines ];
                __CLR4_4_111w11wlpnjv53u.R.inc(1434);instream = new FileInputStream( source );
            } catch( IOException e ) {}
            /* Read the file */
        } }else
            {__CLR4_4_111w11wlpnjv53u.R.inc(1435);System.out.println("Cant access [" + file_name + "]");
    }}finally{__CLR4_4_111w11wlpnjv53u.R.flushNeeded();}}
        
    public TextFrame( URL sourceURL, String fn ) {try{__CLR4_4_111w11wlpnjv53u.R.inc(1436);
        __CLR4_4_111w11wlpnjv53u.R.inc(1437);InputStream source = null;
        __CLR4_4_111w11wlpnjv53u.R.inc(1438);StringBuffer sb;

        __CLR4_4_111w11wlpnjv53u.R.inc(1439);n_lines = 0;
	__CLR4_4_111w11wlpnjv53u.R.inc(1440);start = 0;
        __CLR4_4_111w11wlpnjv53u.R.inc(1441);file_name = sourceURL.toString();

	__CLR4_4_111w11wlpnjv53u.R.inc(1442);try {
	    __CLR4_4_111w11wlpnjv53u.R.inc(1443);source = sourceURL.openStream();
	} catch( IOException e ) {
        }

        __CLR4_4_111w11wlpnjv53u.R.inc(1444);if ( (((source != null )&&(__CLR4_4_111w11wlpnjv53u.R.iget(1445)!=0|true))||(__CLR4_4_111w11wlpnjv53u.R.iget(1446)==0&false))) {{
            __CLR4_4_111w11wlpnjv53u.R.inc(1447);lines = new String[ max_lines ];

            /* Read the file */
            __CLR4_4_111w11wlpnjv53u.R.inc(1448);DataInputStream ds = new DataInputStream( source );
            __CLR4_4_111w11wlpnjv53u.R.inc(1449);n_lines = ReadSource( ds );
        } 
                        
        }__CLR4_4_111w11wlpnjv53u.R.inc(1450);highlight = -1;        
    }finally{__CLR4_4_111w11wlpnjv53u.R.flushNeeded();}}

    public void Highlight( int h ) {try{__CLR4_4_111w11wlpnjv53u.R.inc(1451);
        __CLR4_4_111w11wlpnjv53u.R.inc(1452);highlight = h;
        __CLR4_4_111w11wlpnjv53u.R.inc(1453);this.repaint();
    }finally{__CLR4_4_111w11wlpnjv53u.R.flushNeeded();}}

    public Dimension getPreferredSize() {try{__CLR4_4_111w11wlpnjv53u.R.inc(1454);
        __CLR4_4_111w11wlpnjv53u.R.inc(1455);int h = line_space * (n_lines + 1) - start * line_space;
        __CLR4_4_111w11wlpnjv53u.R.inc(1456);return new Dimension( width, h );
    }finally{__CLR4_4_111w11wlpnjv53u.R.flushNeeded();}}
    
    public Dimension getMinimumSize() {try{__CLR4_4_111w11wlpnjv53u.R.inc(1457);
        __CLR4_4_111w11wlpnjv53u.R.inc(1458);int h = line_space * (n_lines + 1) - start * line_space;
        __CLR4_4_111w11wlpnjv53u.R.inc(1459);return new Dimension( width, h );
    }finally{__CLR4_4_111w11wlpnjv53u.R.flushNeeded();}}    

    public void update(Graphics g) {try{__CLR4_4_111w11wlpnjv53u.R.inc(1460);
        __CLR4_4_111w11wlpnjv53u.R.inc(1461);Dimension d = size();

        __CLR4_4_111w11wlpnjv53u.R.inc(1462);if ((((d.width < 1 || d.height < 1)&&(__CLR4_4_111w11wlpnjv53u.R.iget(1463)!=0|true))||(__CLR4_4_111w11wlpnjv53u.R.iget(1464)==0&false)))
            {__CLR4_4_111w11wlpnjv53u.R.inc(1465);return;

        }__CLR4_4_111w11wlpnjv53u.R.inc(1466);if (((((offscreen == null) || (d.width != offscreensize.width) ||
                (d.height != offscreensize.height))&&(__CLR4_4_111w11wlpnjv53u.R.iget(1467)!=0|true))||(__CLR4_4_111w11wlpnjv53u.R.iget(1468)==0&false))) {{
            __CLR4_4_111w11wlpnjv53u.R.inc(1469);offscreen = createImage(d.width, d.height);
            __CLR4_4_111w11wlpnjv53u.R.inc(1470);offscreensize = d;
            __CLR4_4_111w11wlpnjv53u.R.inc(1471);offGraphics = offscreen.getGraphics();
        }

        }__CLR4_4_111w11wlpnjv53u.R.inc(1472);offGraphics.setColor(Color.white);
        __CLR4_4_111w11wlpnjv53u.R.inc(1473);offGraphics.fillRect(0, 0, d.width, d.height);
        __CLR4_4_111w11wlpnjv53u.R.inc(1474);paint(offGraphics);
        __CLR4_4_111w11wlpnjv53u.R.inc(1475);g.drawImage(offscreen, 0, 0, null);
    }finally{__CLR4_4_111w11wlpnjv53u.R.flushNeeded();}}
    
    public void setStart(int start) {try{__CLR4_4_111w11wlpnjv53u.R.inc(1476);
	__CLR4_4_111w11wlpnjv53u.R.inc(1477);this.start = start;
    }finally{__CLR4_4_111w11wlpnjv53u.R.flushNeeded();}}

    public void paint( Graphics g ) {try{__CLR4_4_111w11wlpnjv53u.R.inc(1478);
        __CLR4_4_111w11wlpnjv53u.R.inc(1479);int i, x, y;

        __CLR4_4_111w11wlpnjv53u.R.inc(1480);SetFont( g );
        __CLR4_4_111w11wlpnjv53u.R.inc(1481);x = dx; __CLR4_4_111w11wlpnjv53u.R.inc(1482);y = line_space;

        __CLR4_4_111w11wlpnjv53u.R.inc(1483);g.setColor( Color.black );
        __CLR4_4_111w11wlpnjv53u.R.inc(1484);for (i=start;(((i<n_lines)&&(__CLR4_4_111w11wlpnjv53u.R.iget(1485)!=0|true))||(__CLR4_4_111w11wlpnjv53u.R.iget(1486)==0&false));i++) {{
            __CLR4_4_111w11wlpnjv53u.R.inc(1487);if( (((i == highlight )&&(__CLR4_4_111w11wlpnjv53u.R.iget(1488)!=0|true))||(__CLR4_4_111w11wlpnjv53u.R.iget(1489)==0&false))) {{
                __CLR4_4_111w11wlpnjv53u.R.inc(1490);g.setColor( Color.red );
            }
            }__CLR4_4_111w11wlpnjv53u.R.inc(1491);g.drawString( lines[i], x, y );
            __CLR4_4_111w11wlpnjv53u.R.inc(1492);g.setColor( Color.black );
            __CLR4_4_111w11wlpnjv53u.R.inc(1493);y += line_space;                
        }
        //g.drawString("EOF",x,y);
    }}finally{__CLR4_4_111w11wlpnjv53u.R.flushNeeded();}}
}



