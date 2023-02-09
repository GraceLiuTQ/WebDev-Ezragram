<script>
	import { pb } from '$lib/pocketbase';
	import { onMount } from 'svelte';

	let posts = [];

	onMount(async () => {
		const resultList = await pb.collection('posts').getList(1, 50, {});
		sort: '-created';
		expand: 'user';
		posts = resultList.items;
	});
</script>

<div class="flex justify-center">
	<div class="w-full max-w-lg p-4">
		{#each posts as post}
			<div class="mb-4 flex flex-col gap-2 rounded-lg border-2 border-base-300 py-2">
				<!-- author -->
				<div class="flex justify-between py-2 px-4">
					<div class="flex items-center gap-2">
						<!-- avatar -->
						<div class="placeholder avatar">
							<div class="w-8 rounded-full bg-neutral-focus text-neutral-content">
								<span class="text-md">{post.expand.user.username.charAt(0)}</span>
							</div>
						</div>

						<!-- username -->
						<p class="text-sm font-medium">{post.expand.user.username}</p>
					</div>
				</div>
				<!-- photo -->
				<div class="w-full">
					<img src={pb.getFileUrl(post, post?.photo)} class="h-[500px] w-full" />
				</div>

				<div class="py-2 px-4">
					<!-- caption -->
					<p class="mb-4 text-sm">{post.caption}</p>

					<!-- date -->
					<p class="text-xs">{post.created}</p>
				</div>
			</div>
		{/each}
	</div>
</div>
