/**
 *                            License
 * THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS  
 * CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
 * THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW.  
 * ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE OR  
 * COPYRIGHT LAW IS PROHIBITED.
 * 
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND  
 * AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE  
 * MAY BE CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package l1j.server.server.command.executor;

import java.util.StringTokenizer;

import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SystemMessage;

/**
 * GM指令：全體聊天
 */
public class L1Chat implements L1CommandExecutor {
	private L1Chat() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1Chat();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		try {
			StringTokenizer st = new StringTokenizer(arg);
			if (st.hasMoreTokens()) {
				String flag = st.nextToken();
				String msg;
				if (flag.compareToIgnoreCase("on") == 0) {
					L1World.getInstance().set_worldChatElabled(true);
					msg = "Global chat turned on.";
				}
				else if (flag.compareToIgnoreCase("off") == 0) {
					L1World.getInstance().set_worldChatElabled(false);
					msg = "Global chat turned off.";
				}
				else {
					throw new Exception();
				}
				pc.sendPackets(new S_SystemMessage(msg));
			}
			else {
				String msg;
				if (L1World.getInstance().isWorldChatElabled()) {
					msg = "Global chat turned on.";
				}
				else {
					msg = "Global chat turned off.";
				}
				pc.sendPackets(new S_SystemMessage(msg));
			}
		}
		catch (Exception e) {
			pc.sendPackets(new S_SystemMessage("Please enter " + cmdName + " [on|off]"));
		}
	}
}
