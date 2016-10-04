package salesforceflow;

import com.virtual1.salesforcebox.sf.model.Attachment;
import com.virtual1.salesforcebox.sf.model.Contact;
import org.junit.Assert;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:13
 */
public class AttachmentTestFlow extends AbstractTestFlow {


    public Attachment findExisting() {
        Attachment attachment = getSalesforceService().getAttachment(ATTACHMENT_ID);
        Assert.assertNotNull(attachment);
        return attachment;
    }

    public Attachment create() {
        Attachment attachment = new Attachment();
        attachment.setName("auto-test.png");
        attachment.setParentId(ATTACHMENT_PARENT_ID);
        attachment.setBody(getTestAttachment());

        attachment = getSalesforceService().create(attachment);

        Assert.assertNotNull(attachment.getId());
        try {
            checkInSalesforce(attachment);
        } catch (Exception e) {
            delete(attachment.getId());
            throw e;
        }

        return attachment;
    }

    public Attachment update(Attachment attachment) {
        attachment.setName(randomString());
        attachment.setParentId(ATTACHMENT_PARENT_ID);
        attachment.setBody(getTestAttachment());

        getSalesforceService().update(attachment);
        checkInSalesforce(attachment);
        return attachment;
    }

    private void checkInSalesforce(Attachment attachment) {
        Attachment inSf = getSalesforceService().getAttachment(attachment.getId());
        assertEquals(attachment, inSf);
    }

    public void delete(String id) {
        getSalesforceService().delete(id);
        Contact contact = getSalesforceService().getContact(id);
        Assert.assertNull(contact);
    }

    private void assertEquals(Attachment o1, Attachment o2) {
        Assert.assertEquals(o1.getId(), o2.getId());
        Assert.assertEquals(o1.getName(), o2.getName());
        Assert.assertEquals(formatSfId(o1.getParentId()), formatSfId(o2.getParentId()));
        Assert.assertArrayEquals(o1.getBody(), o2.getBody());
    }


}
