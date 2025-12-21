/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;
import java.util.ArrayList;
import java.util.List;
/*
 * Represents an internal node in the B+Tree
 * @author	Lidan Hifi
 */
class InternalNode<Value> extends Node<Value> {public static class __CLR4_4_14242m7es043n{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0031\u0032\u0033\u005c\u005c\u0031\u0031\u0039\u005c\u005c\u0042\u0050\u006c\u0075\u0073\u0054\u0072\u0065\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1740142646498L,8589935092L,273,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
	// children vector- pointers to the node's children nodes
	private List<Node<Value>> children;
	// how many leaves is stored in the subtree rooted in this node
	private int totalWeight = 0;
	
	/*
	 * creates new internal node
	 * @param totalKeys treeParam
	 */
	public InternalNode(int totalKeys) {
		super(totalKeys);__CLR4_4_14242m7es043n.R.inc(147);try{__CLR4_4_14242m7es043n.R.inc(146);
		__CLR4_4_14242m7es043n.R.inc(148);children = new ArrayList<Node<Value>>(t);
	}finally{__CLR4_4_14242m7es043n.R.flushNeeded();}}
	
	/*
	 * creates new internal node, with the given keys list and the given children pointers list
	 * @param totalKeys tree param
	 * @param keys list of integer keys
	 * @param children list of children nodes
	 */
	public InternalNode(int totalKeys, List<Integer> keys, List<Node<Value>> children) {
		this(totalKeys);__CLR4_4_14242m7es043n.R.inc(150);try{__CLR4_4_14242m7es043n.R.inc(149);
		__CLR4_4_14242m7es043n.R.inc(151);this.keys.addAll(keys);
		__CLR4_4_14242m7es043n.R.inc(152);this.children.addAll(children);
		
		__CLR4_4_14242m7es043n.R.inc(153);for (Node<Value> child : children)
			{__CLR4_4_14242m7es043n.R.inc(154);this.totalWeight += child.getNodeSize();
	}}finally{__CLR4_4_14242m7es043n.R.flushNeeded();}}
	
	/*
	 * creates new internal node based on a given split
	 * @param totalKeys
	 * @param split insertion result that returned after splitting a child node
	 */
	public InternalNode(int totalKeys, InsertionResult<Value> split) {
		this(totalKeys);__CLR4_4_14242m7es043n.R.inc(156);try{__CLR4_4_14242m7es043n.R.inc(155);
		
		// add the left, right children nodes from the split to the new node
		__CLR4_4_14242m7es043n.R.inc(157);children.add(split.getLeftNode());
		__CLR4_4_14242m7es043n.R.inc(158);children.add(split.getRightNode());
		
		// add the new root to the new node
		__CLR4_4_14242m7es043n.R.inc(159);keys.add(split.getSplitRootKey());
		
		// calculate the total weight of the new node
		__CLR4_4_14242m7es043n.R.inc(160);totalWeight = split.getLeftNode().getNodeSize() + split.getRightNode().getNodeSize();
	}finally{__CLR4_4_14242m7es043n.R.flushNeeded();}}
	
	/*
	 * (non-Javadoc)
	 * @see data.Node#getNodeSize()
	 */
	public int getNodeSize() {try{__CLR4_4_14242m7es043n.R.inc(161);
		__CLR4_4_14242m7es043n.R.inc(162);return totalWeight;
	}finally{__CLR4_4_14242m7es043n.R.flushNeeded();}}
	
	/*
	 * (non-Javadoc)
	 * @see data.Node#order(int)
	 */
	public int order(int key) {try{__CLR4_4_14242m7es043n.R.inc(163);
		__CLR4_4_14242m7es043n.R.inc(164);int orderIndex = 0;
		__CLR4_4_14242m7es043n.R.inc(165);int childOrderIndex = 0;
		
		// right most- check if the key is larger than any other key in this node
		__CLR4_4_14242m7es043n.R.inc(166);if ((((keys.get(keys.size() - 1).compareTo(key) < 0)&&(__CLR4_4_14242m7es043n.R.iget(167)!=0|true))||(__CLR4_4_14242m7es043n.R.iget(168)==0&false))) {{
			// calculate the total weight of all the children until the last child node
			// continue calculate the order recursively
			__CLR4_4_14242m7es043n.R.inc(169);orderIndex = this.totalWeight - children.get(children.size() - 1).getNodeSize();
			__CLR4_4_14242m7es043n.R.inc(170);childOrderIndex = children.get(children.size() - 1).order(key);
			__CLR4_4_14242m7es043n.R.inc(171);if ((((childOrderIndex != -1)&&(__CLR4_4_14242m7es043n.R.iget(172)!=0|true))||(__CLR4_4_14242m7es043n.R.iget(173)==0&false)))
				{__CLR4_4_14242m7es043n.R.inc(174);orderIndex += childOrderIndex;
			}else
				{__CLR4_4_14242m7es043n.R.inc(175);return -1;
		}} }else {{
			__CLR4_4_14242m7es043n.R.inc(176);int i = 0;
			// in the middle of the list- calculate the total weight of all the children while the current key is smaller than the current key
			// continue calculate the order recursively
			__CLR4_4_14242m7es043n.R.inc(177);Integer currentKey = keys.get(i);
			__CLR4_4_14242m7es043n.R.inc(178);while ((((i < keys.size() && currentKey.compareTo(key) < 0)&&(__CLR4_4_14242m7es043n.R.iget(179)!=0|true))||(__CLR4_4_14242m7es043n.R.iget(180)==0&false))) {{
				__CLR4_4_14242m7es043n.R.inc(181);childOrderIndex = children.get(i).getNodeSize();
				__CLR4_4_14242m7es043n.R.inc(182);if ((((childOrderIndex == -1)&&(__CLR4_4_14242m7es043n.R.iget(183)!=0|true))||(__CLR4_4_14242m7es043n.R.iget(184)==0&false)))
					{__CLR4_4_14242m7es043n.R.inc(185);return -1;
				}else
					{__CLR4_4_14242m7es043n.R.inc(186);orderIndex += childOrderIndex;
				
				}__CLR4_4_14242m7es043n.R.inc(187);if ((((i < keys.size() - 1)&&(__CLR4_4_14242m7es043n.R.iget(188)!=0|true))||(__CLR4_4_14242m7es043n.R.iget(189)==0&false)))
					{__CLR4_4_14242m7es043n.R.inc(190);currentKey = keys.get(++i);
				}else
					{__CLR4_4_14242m7es043n.R.inc(191);i++;
			}}
			
			}__CLR4_4_14242m7es043n.R.inc(192);orderIndex += children.get(i).order(key);
		}
		
		}__CLR4_4_14242m7es043n.R.inc(193);return orderIndex;
	}finally{__CLR4_4_14242m7es043n.R.flushNeeded();}}
	
	/*
	 * (non-Javadoc)
	 * @see data.Node#insert(int, java.lang.Object)
	 */
	public InsertionResult<Value> insert(int key, Value value) {try{__CLR4_4_14242m7es043n.R.inc(194);
		// insert key,value recursively (until reaches a leaf) and calculate the new node's weight after the insertion
		// may be not changed, if the key is already exists (in this case we update the value only, but the weight does not change)
		__CLR4_4_14242m7es043n.R.inc(195);Node<Value> child = getChildNode(key);
		__CLR4_4_14242m7es043n.R.inc(196);totalWeight -= child.getNodeSize();
		__CLR4_4_14242m7es043n.R.inc(197);InsertionResult<Value> split = child.insert(key, value);
		__CLR4_4_14242m7es043n.R.inc(198);totalWeight += child.getNodeSize();
		
		// if the child node was split as a result of the insertion
		__CLR4_4_14242m7es043n.R.inc(199);if ((((split != null && split.getSplitRootKey() != null)&&(__CLR4_4_14242m7es043n.R.iget(200)!=0|true))||(__CLR4_4_14242m7es043n.R.iget(201)==0&false))) {{
			__CLR4_4_14242m7es043n.R.inc(202);int indexForInsertion = findLessThanOrEqualToKey(split.getSplitRootKey());
			
			__CLR4_4_14242m7es043n.R.inc(203);if ((((keys.size() < t - 1)&&(__CLR4_4_14242m7es043n.R.iget(204)!=0|true))||(__CLR4_4_14242m7es043n.R.iget(205)==0&false))) {{
				// if this node is not full- insert the new root key to this node
				__CLR4_4_14242m7es043n.R.inc(206);insertNonFull(split, indexForInsertion);
			} }else {{
				// if this node is full- SPLIT the keys and the children
				__CLR4_4_14242m7es043n.R.inc(207);InternalNode<Value> nextSibling;
				
				__CLR4_4_14242m7es043n.R.inc(208);int mid = 0;
				__CLR4_4_14242m7es043n.R.inc(209);if ((((t % 2 == 0)&&(__CLR4_4_14242m7es043n.R.iget(210)!=0|true))||(__CLR4_4_14242m7es043n.R.iget(211)==0&false))) {{
					__CLR4_4_14242m7es043n.R.inc(212);mid = keys.size() / 2;
				} }else {{
					__CLR4_4_14242m7es043n.R.inc(213);mid = (keys.size() + 1) / 2;
				}
								
				// move keys & children to the next node, and make insertion- based on the original index and the mid
				}__CLR4_4_14242m7es043n.R.inc(214);if ((((indexForInsertion <= mid)&&(__CLR4_4_14242m7es043n.R.iget(215)!=0|true))||(__CLR4_4_14242m7es043n.R.iget(216)==0&false))) {{
					// insert the new root key to the current node
					__CLR4_4_14242m7es043n.R.inc(217);nextSibling = new InternalNode<Value>(t, this.keys.subList(mid, this.keys.size()), this.children.subList(mid, this.children.size()));
					__CLR4_4_14242m7es043n.R.inc(218);this.keys = new ArrayList<Integer>(this.keys.subList(0, mid));
					__CLR4_4_14242m7es043n.R.inc(219);this.children = new ArrayList<Node<Value>>(this.children.subList(0, mid));
					__CLR4_4_14242m7es043n.R.inc(220);totalWeight -= nextSibling.getNodeSize();
					
					__CLR4_4_14242m7es043n.R.inc(221);insertNonFull(split);
				} }else {{
					// insert the new root key to the new sibling
					__CLR4_4_14242m7es043n.R.inc(222);nextSibling = new InternalNode<Value>(t, this.keys.subList(mid + 1, this.keys.size()), this.children.subList(mid + 1, this.children.size()));
					__CLR4_4_14242m7es043n.R.inc(223);this.keys = new ArrayList<Integer>(this.keys.subList(0, mid + 1));
					__CLR4_4_14242m7es043n.R.inc(224);this.children = new ArrayList<Node<Value>>(this.children.subList(0, mid + 1));
					__CLR4_4_14242m7es043n.R.inc(225);totalWeight -= nextSibling.getNodeSize();
					
					__CLR4_4_14242m7es043n.R.inc(226);nextSibling.insertNonFull(split);
				}
				
				// remove the new root from the key's list
				}__CLR4_4_14242m7es043n.R.inc(227);Integer newRoot = this.keys.get(this.keys.size() - 1);
				__CLR4_4_14242m7es043n.R.inc(228);this.keys.remove(this.keys.size() - 1);
				
				// the node was split, so return a new insertion result that holds the split
				__CLR4_4_14242m7es043n.R.inc(229);return new InsertionResult<Value>(newRoot, this, nextSibling, split);
			}
		}}
		
		}__CLR4_4_14242m7es043n.R.inc(230);return new InsertionResult<Value>(split.getMinGap());
	}finally{__CLR4_4_14242m7es043n.R.flushNeeded();}}
	
	/*
	 * insert the new root (as a result of split) to a non-full internal node
	 * @param split split that contains pointers to the right & left nodes (as a result of the split), and the new root
	 */
	private void insertNonFull(InsertionResult<Value> split) {try{__CLR4_4_14242m7es043n.R.inc(231);
		__CLR4_4_14242m7es043n.R.inc(232);insertNonFull(split, findLessThanOrEqualToKey(split.getSplitRootKey()));
	}finally{__CLR4_4_14242m7es043n.R.flushNeeded();}}
	
	/*
	 * insert the new root (as a result of split) to a non-full internal node
	 * @param split split that contains pointers to the right & left nodes (as a result of the split), and the new root
	 * @param index index for insertion
	 */
	private void insertNonFull(InsertionResult<Value> split, int index) {try{__CLR4_4_14242m7es043n.R.inc(233);
		__CLR4_4_14242m7es043n.R.inc(234);if ((((keys.isEmpty() || index == keys.size())&&(__CLR4_4_14242m7es043n.R.iget(235)!=0|true))||(__CLR4_4_14242m7es043n.R.iget(236)==0&false))) {{ // empty or right most
			__CLR4_4_14242m7es043n.R.inc(237);keys.add(split.getSplitRootKey());
			
			__CLR4_4_14242m7es043n.R.inc(238);if ((((children.isEmpty())&&(__CLR4_4_14242m7es043n.R.iget(239)!=0|true))||(__CLR4_4_14242m7es043n.R.iget(240)==0&false))) {{ // empty
				__CLR4_4_14242m7es043n.R.inc(241);addChild(split.getLeftNode());
			} }else {{ // right most
				__CLR4_4_14242m7es043n.R.inc(242);setChild(children.size() - 1, split.getLeftNode());
			}
			
			}__CLR4_4_14242m7es043n.R.inc(243);addChild(split.getRightNode());
		} }else {{
			//in the middle of the list
			__CLR4_4_14242m7es043n.R.inc(244);keys.add(index, split.getSplitRootKey());
			
			__CLR4_4_14242m7es043n.R.inc(245);setChild(index, split.getLeftNode());
			
			__CLR4_4_14242m7es043n.R.inc(246);if ((((index == 0)&&(__CLR4_4_14242m7es043n.R.iget(247)!=0|true))||(__CLR4_4_14242m7es043n.R.iget(248)==0&false))) {{ // beginning of list
				__CLR4_4_14242m7es043n.R.inc(249);addChild(index + 1, split.getRightNode());
			} }else {{ // middle of list
				__CLR4_4_14242m7es043n.R.inc(250);addChild(index + 1, split.getRightNode());
			}
			
		}}
	}}finally{__CLR4_4_14242m7es043n.R.flushNeeded();}}
	
	/*
	 * add a child node in a specific index (in the middle of a children list), and update the node's weight based on the new node's weight
	 * @param index index in the children list
	 * @param node new node for insertion
	 */
	private void addChild(int index, Node<Value> node) {try{__CLR4_4_14242m7es043n.R.inc(251);
		__CLR4_4_14242m7es043n.R.inc(252);children.add(index, node);
		__CLR4_4_14242m7es043n.R.inc(253);totalWeight += node.getNodeSize();
	}finally{__CLR4_4_14242m7es043n.R.flushNeeded();}}
	
	/*
	 * add new child node, and update the node's weight based on the new node's weight
	 * @param index index in the children list
	 * @param node new node for insertion 
	 */
	private void addChild(Node<Value> node) {try{__CLR4_4_14242m7es043n.R.inc(254);
		__CLR4_4_14242m7es043n.R.inc(255);children.add(node);
		__CLR4_4_14242m7es043n.R.inc(256);totalWeight += node.getNodeSize();
	}finally{__CLR4_4_14242m7es043n.R.flushNeeded();}}
	
	/*
	 * replace already-exists-child-node with an other node, and update the node's weight based on the new node's weight
	 * @param index index in the children list
	 * @param node new node for replace 
	 */
	private void setChild(int index, Node<Value> node) {try{__CLR4_4_14242m7es043n.R.inc(257);
		__CLR4_4_14242m7es043n.R.inc(258);totalWeight -= children.get(index).getNodeSize();
		__CLR4_4_14242m7es043n.R.inc(259);children.set(index, node);
		__CLR4_4_14242m7es043n.R.inc(260);totalWeight += node.getNodeSize();
	}finally{__CLR4_4_14242m7es043n.R.flushNeeded();}}
	
	/*
	 * get the child node that matches to the given key
	 * @param key key
	 * @return child node that matches to the given key
	 */
	public Node<Value> getChildNode(int key) {try{__CLR4_4_14242m7es043n.R.inc(261);
		__CLR4_4_14242m7es043n.R.inc(262);return children.get(findLessThanOrEqualToKey(key));
	}finally{__CLR4_4_14242m7es043n.R.flushNeeded();}}
	
	/*
	 * get the right most child node
	 * @return pointer to the right most child node
	 */
	public Node<Value> getMaxChildNode() {try{__CLR4_4_14242m7es043n.R.inc(263);
		__CLR4_4_14242m7es043n.R.inc(264);return children.get(children.size() - 1);
	}finally{__CLR4_4_14242m7es043n.R.flushNeeded();}} 
	
	/*
	 * get string representation of the keys stored under this node in ascending order- from the first key to the last.
	 * the root of each leaf is represented by "#".
	 * @return string representation of the node's keys in ascending order
	 */
	public String toString() {try{__CLR4_4_14242m7es043n.R.inc(265);
		__CLR4_4_14242m7es043n.R.inc(266);StringBuilder sb = new StringBuilder();
		
		__CLR4_4_14242m7es043n.R.inc(267);for (int i = 0; (((i < this.children.size() - 1)&&(__CLR4_4_14242m7es043n.R.iget(268)!=0|true))||(__CLR4_4_14242m7es043n.R.iget(269)==0&false)); i++) {{
			__CLR4_4_14242m7es043n.R.inc(270);sb.append(this.children.get(i).toString() + "#");
		}
		}__CLR4_4_14242m7es043n.R.inc(271);sb.append(this.children.get(this.children.size() - 1).toString());
		
		__CLR4_4_14242m7es043n.R.inc(272);return sb.toString();
	}finally{__CLR4_4_14242m7es043n.R.flushNeeded();}}
}
