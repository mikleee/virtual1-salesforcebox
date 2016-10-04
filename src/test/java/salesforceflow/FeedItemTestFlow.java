package salesforceflow;

import com.virtual1.salesforcebox.sf.model.Contact;
import com.virtual1.salesforcebox.sf.model.FeedItem;
import org.junit.Assert;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:13
 */
public class FeedItemTestFlow extends AbstractTestFlow {

    public FeedItem findExisting() {
        FeedItem feedItem = getSalesforceService().getFeedItem(FEED_ITEM_ID);
        Assert.assertNotNull(feedItem);
        return feedItem;
    }

    public FeedItem create() {
        FeedItem feedItem = new FeedItem();
        feedItem.setParentId(ATTACHMENT_PARENT_ID);
        feedItem.setBody(randomString());
        feedItem.setContentFileName("feed-item-file.png");
        feedItem.setContentData(getTestAttachment());

        feedItem = getSalesforceService().create(feedItem);

        Assert.assertNotNull(feedItem.getId());
        try {
            checkInSalesforce(feedItem);
        } catch (Exception e) {
            delete(feedItem.getId());
            throw e;
        }

        return feedItem;
    }

    private void checkInSalesforce(FeedItem feedItem) {
        FeedItem inSf = getSalesforceService().getFeedItem(feedItem.getId());
        assertEquals(feedItem, inSf);
    }

    public void delete(String id) {
        getSalesforceService().delete(new String[]{id});
        Contact contact = getSalesforceService().getContact(id);
        Assert.assertNull(contact);
    }

    private void assertEquals(FeedItem o1, FeedItem o2) {
        Assert.assertEquals(o1.getId(), o2.getId());
        Assert.assertEquals(o1.getName(), o2.getName());
        Assert.assertEquals(formatSfId(o1.getParentId()), formatSfId(o2.getParentId()));
        Assert.assertEquals(o1.getBody(), o2.getBody());
        Assert.assertEquals(o1.getContentFileName(), o2.getContentFileName());
        Assert.assertArrayEquals(o1.getContentData(), o2.getContentData());
    }


}
