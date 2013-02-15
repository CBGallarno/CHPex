package com.brothercraft.chpex;

import com.laytonsmith.annotations.api;
import com.laytonsmith.annotations.shutdown;
import com.laytonsmith.annotations.startup;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.functions.Exceptions.ExceptionType;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CHPex {
    
    @startup
    public static void onEnable(){
	Static.checkPlugin("PermissionsEx", Target.UNKNOWN);
	System.out.println("[CommandHelper] CHPex Initialized - ACzChef");
    }
    
    @shutdown
    public static void onDisable(){
	   System.out.println("[CommandHelper] CHPex De-Initialized - ACzChef");
    }
    
    @api
    public static class pex_get_group_users extends AbstractFunction {
        public String getName() {
                return "pex_get_group_users";
        
	}
	public Integer[] numArgs() {
		return new Integer[]{1};
	}
	
	public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {
	    Static.checkPlugin("PermissionsEx", t);
	    PermissionManager pex = PermissionsEx.getPermissionManager();
	    String g;
	    CArray cusers = new CArray(t);
	    if (args[0] instanceof CString) {
		g = args[0].val();
	    } else {
		return new CVoid(t);
	    }
	    PermissionUser[] users = pex.getUsers(g);
	    
	    for (PermissionUser user : users) {
		cusers.push(new CString(user.getName(), t));
	    }
		return cusers;
	}

	public ExceptionType[] thrown() {
	    return new ExceptionType[]{ExceptionType.CastException, ExceptionType.FormatException};
	}

	public boolean isRestricted() {
	    return true;
	}

	public Boolean runAsync() {
	    return false;
	}

	public String docs() {
	    throw new UnsupportedOperationException("Not supported yet.");
	}

	public CHVersion since() {
	    return CHVersion.V3_3_1;
	}
    }
}