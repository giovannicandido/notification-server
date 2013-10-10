
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MySimpleBeamTest
{
   @Inject
   private MySimpleBeam mysimplebeam;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class, "test.jar")
            .addClass(MySimpleBeam.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void testIsDeployed()
   {
       Assert.assertNotNull(mysimplebeam);
       Assert.assertEquals("Hello World", mysimplebeam.helloWorld());
   }
}
