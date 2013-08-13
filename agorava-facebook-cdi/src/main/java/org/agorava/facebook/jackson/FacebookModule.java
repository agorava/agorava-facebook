/*
 * Copyright 2013 Agorava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.agorava.facebook.jackson;

import org.agorava.Facebook;
import org.agorava.facebook.jackson.PhotoMixin.ImageMixin;
import org.agorava.facebook.model.*;
import org.agorava.facebook.model.Photo.Image;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.module.SimpleModule;

/**
 * Jackson module for setting up mixin annotations on Facebook model types. This enables the use of Jackson annotations without
 * directly annotating the model classes themselves.
 *
 * @author Craig Walls
 */
@Facebook
public class FacebookModule extends SimpleModule {

    public FacebookModule() {
        super("FacebookModule", new Version(1, 0, 0, null));
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(FacebookProfile.class, FacebookProfileMixin.class);
        context.setMixInAnnotations(WorkEntry.class, WorkEntryMixin.class);
        context.setMixInAnnotations(EducationEntry.class, EducationEntryMixin.class);
        context.setMixInAnnotations(Reference.class, ReferenceMixin.class);
        context.setMixInAnnotations(GroupMemberReference.class, GroupMemberReferenceMixin.class);
        context.setMixInAnnotations(Album.class, AlbumMixin.class);
        context.setMixInAnnotations(Group.class, GroupMixin.class);
        context.setMixInAnnotations(Event.class, EventMixin.class);
        context.setMixInAnnotations(Invitation.class, InvitationMixin.class);
        context.setMixInAnnotations(EventInvitee.class, EventInviteeMixin.class);
        context.setMixInAnnotations(Checkin.class, CheckinMixin.class);
        context.setMixInAnnotations(Page.class, PageMixin.class);
        context.setMixInAnnotations(Location.class, LocationMixin.class);
        context.setMixInAnnotations(Comment.class, CommentMixin.class);
        context.setMixInAnnotations(Tag.class, TagMixin.class);
        context.setMixInAnnotations(Video.class, VideoMixin.class);
        context.setMixInAnnotations(Photo.class, PhotoMixin.class);
        context.setMixInAnnotations(Image.class, ImageMixin.class);
        context.setMixInAnnotations(Post.class, PostMixin.class);
        context.setMixInAnnotations(CheckinPost.class, CheckinPostMixin.class);
        context.setMixInAnnotations(LinkPost.class, LinkPostMixin.class);
        context.setMixInAnnotations(NotePost.class, NotePostMixin.class);
        context.setMixInAnnotations(PhotoPost.class, PhotoPostMixin.class);
        context.setMixInAnnotations(StatusPost.class, StatusPostMixin.class);
        context.setMixInAnnotations(VideoPost.class, VideoPostMixin.class);
        context.setMixInAnnotations(Account.class, AccountMixin.class);
        context.setMixInAnnotations(SwfPost.class, SwfPostMixin.class);
        context.setMixInAnnotations(MusicPost.class, MusicPostMixin.class);
        context.setMixInAnnotations(GroupMembership.class, GroupMembershipMixin.class);
        context.setMixInAnnotations(FamilyMember.class, FamilyMemberMixin.class);
        context.setMixInAnnotations(Question.class, QuestionMixin.class);
        context.setMixInAnnotations(QuestionOption.class, QuestionOptionMixin.class);
    }
}
