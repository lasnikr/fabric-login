package fabric.login.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.util.Session;

@Mixin(Session.class)
public interface SessionMixin {
	@Accessor("username")
	void setUsername(String username);
}
